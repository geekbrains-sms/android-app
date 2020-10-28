package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

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
                .map(x -> productTransactionMapper.toEntityList(x))
                .doOnNext(x -> {
                    productTransactionDao.deleteAll(x);
                    productTransactionDao.insertAll(x);})
                .flatMapIterable(x -> x)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> crossDao.insert(new ProductTransactionCrossRef(x.productId, x.id))));
    }

    @Override
    public Flowable<List<ProductTransaction>> getProductTransactionsFromDB() {
        return productTransactionDao.getAllTransaction();
    }

    @Override
    public Completable addProductTransactions(ProductTransaction productTransaction) {
        Observable<List<ProductTransactionDTO>>productTransactions;
        if(productTransaction.quantity > 0)
        {
            productTransactions = productTransactionService.addSupplyTransactions(productTransaction);
        }
        else
        {
            productTransactions = productTransactionService.addShipmentTransactions(productTransaction);
        }
        return productTransactions
                .map(x -> productTransactionMapper.toEntityList(x))
                .doOnNext(x -> {
                    productTransactionDao.deleteAll(x);
                    productTransactionDao.insertAll(x);
                })
                .flatMapIterable(x -> x)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> crossDao.insert(new ProductTransactionCrossRef(x.productId, x.id))));

    }

    @Override
    public Completable saveProductTransactionsByProductIdToDB(long id) {
        return productTransactionService.getProductTransactionById(id)
                .map(x -> productTransactionMapper.toEntityList(x))
                .doOnNext(x -> crossDao.deleteByProduct(id))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> {crossDao.insert(new ProductTransactionCrossRef(id, x.getId()));
                    productTransactionDao.insert(x);
                }));
    }
}
