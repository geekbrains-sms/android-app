package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface ProductTransactionRepository {
    Completable getProductTransactionsFromServer();
    Flowable<List<ProductTransaction>> getProductTransactionsFromDB();
    Completable addProductTransactions(ProductTransaction productTransaction);
    Completable saveProductTransactionsByProductIdToDB(long id);
}
