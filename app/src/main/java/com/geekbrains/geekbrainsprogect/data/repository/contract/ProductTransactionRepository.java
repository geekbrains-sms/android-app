package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;


public interface ProductTransactionRepository {
    Completable getProductTransactionsFromServer();
    Flowable<List<ProductTransactionData>> getProductTransactionsFromDB();
    Observable<ProductTransactionData> addProductTransactions(ProductTransactionModel productTransaction);
    Completable saveProductTransactionsByProductIdToDB(long id);
    Single<List<ProductTransactionData>> getProductTransactionByProductId(long id);
}
