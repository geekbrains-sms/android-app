package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface ProductTransactionRepository {
    Completable getProductTransactionsFromServer();
    Flowable<List<ProductTransaction>> getProductTransactionsFromDB();
    Completable addProductTransactions(ProductTransactionModel productTransaction);
    Completable saveProductTransactionsByProductIdToDB(long id);
}
