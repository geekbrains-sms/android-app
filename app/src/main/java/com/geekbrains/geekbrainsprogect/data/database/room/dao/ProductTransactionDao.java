package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ProductTransactionDao extends BaseDao<ProductTransaction> {
    @Query("SELECT * FROM product_transaction")
    Flowable<List<ProductTransaction>> getAllTransaction();
    @Query("SELECT * FROM product_transaction WHERE product_transaction.id = :id")
    Flowable<ProductTransaction>getTransactionById(long id);
    @Query("DELETE FROM product_transaction")
    Completable deleteAllTransaction();
}
