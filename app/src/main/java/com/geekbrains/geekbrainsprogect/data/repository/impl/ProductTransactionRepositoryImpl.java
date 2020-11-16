package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.model.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UserDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ProductTransactionRepositoryImpl implements ProductTransactionRepository {
    ProductTransactionDao productTransactionDao;
    ProductTransactionCrossDao crossDao;
    ProductTransactionService productTransactionService;
    ProductTransactionMapper productTransactionMapper;


    @Inject
    public ProductTransactionRepositoryImpl(ProductTransactionDao productTransactionDao,
                                            ProductTransactionCrossDao crossDao, ProductTransactionService productTransactionService,
                                            ProductTransactionMapper productTransactionMapper) {
        this.productTransactionDao = productTransactionDao;
        this.crossDao = crossDao;
        this.productTransactionService = productTransactionService;
        this.productTransactionMapper = productTransactionMapper;
    }

    @Override
    public Completable getProductTransactionsFromServer() {
        return productTransactionService.getAllShipmentProductTransactions().mergeWith(productTransactionService.getAllSupplyProductTransactions())
                .map(x -> productTransactionMapper.toEntityListProductTransaction(x))
                .doOnNext(x -> {
                    productTransactionDao.deleteAll(x);
                    productTransactionDao.insertAll(x);})
                .flatMapIterable(x -> x)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> crossDao.insert(new ProductTransactionCrossRef(x.getId(), x.getId()))));
    }

    @Override
    public Flowable<List<ProductTransactionData>> getProductTransactionsFromDB() {
        return productTransactionDao.getAllTransaction();
    }

    @Override
    public Observable<ProductTransactionData> addProductTransactions(ProductTransactionModel productTransaction) {
        Observable<ProductTransactionDTO>productTransactions;
        if(productTransaction.getQuantity() > 0)
        {
            productTransactions = productTransactionService.addSupplyTransactions(productTransactionMapper.toDto(productTransaction));
        }
        else
        {
            productTransactions = productTransactionService.addShipmentTransactions(productTransactionMapper.toDto(productTransaction));
        }
        return productTransactions
                .map(x -> productTransactionMapper.toEntity(x))
                .doOnNext(x -> {
                    productTransactionDao.insert(x.getProductTransaction());
                    crossDao.insert(new ProductTransactionCrossRef(x.getProductId(), x.getProductTransaction().getId()));
                });

    }

    @Override
    public Completable saveProductTransactionsByProductIdToDB(long id) {
        return productTransactionService.getProductTransactionById(id)
                .map(x -> productTransactionMapper.toEntityList(x))
                .doOnNext(x -> crossDao.deleteByProduct(id))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> {crossDao.insert(new ProductTransactionCrossRef(id, x.getId()));
                    productTransactionDao.insert(x.getProductTransaction());
                }));
    }

    @Override
    public Single<List<ProductTransactionData>> getProductTransactionByProductId(long id) {
        return saveProductTransactionsByProductIdToDB(id).andThen(productTransactionDao.getTransactionsByProductId(id));
    }
}
