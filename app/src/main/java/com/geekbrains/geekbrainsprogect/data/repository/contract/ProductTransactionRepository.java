package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public interface ProductTransactionRepository {
    Completable getProductTransactionsFromServer();
    Flowable<List<ProductTransactionData>> getProductTransactionsFromDB();
    Completable addProductTransactions(ProductTransactionModel productTransaction);
    Completable saveProductTransactionsByProductIdToDB(long id);
    Flowable<List<ProductTransactionData>>getProductTransactionByProductId(long id);
}
