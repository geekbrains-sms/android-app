package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public abstract class ProductTransactionDao extends BaseDao<ProductTransaction> {
    @Query("SELECT * FROM product_transaction")
    abstract Flowable<List<ProductTransaction>> getAllTransaction();
    @Query("SELECT * FROM product_transaction WHERE product_transaction.transactionId = :id")
    abstract Flowable<ProductTransaction>getTransactionById(long id);
    @Query("DELETE FROM product_transaction")
    abstract Completable deleteAllTransaction();
}
