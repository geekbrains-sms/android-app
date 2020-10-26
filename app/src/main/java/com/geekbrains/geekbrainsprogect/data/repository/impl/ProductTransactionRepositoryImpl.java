package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ProductTransactionRepositoryImpl implements ProductTransactionRepository {
    ProductTransactionDao productTransactionDao;
    ProductTransactionService productTransactionService;
    @Override
    public Completable getProductTransactionsFromServer() {
        return null;
    }

    @Override
    public Flowable<List<ProductTransaction>> getProductTransactionsFromDB() {
        return null;
    }

    @Override
    public Completable addProductTransactions(ProductTransaction productTransaction) {
        return null;
    }

    @Override
    public Observable<List<ProductTransaction>> getProductTransactionsByProductId(long id) {
        return null;
    }
}
