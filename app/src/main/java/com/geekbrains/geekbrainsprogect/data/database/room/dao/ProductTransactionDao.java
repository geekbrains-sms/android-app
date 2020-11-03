package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class ProductTransactionDao extends BaseDao<ProductTransaction> {
    @Transaction
    @Query("SELECT * FROM product_transaction")
    public abstract Flowable<List<ProductTransactionData>> getAllTransaction();
    @Transaction
    @Query("SELECT * FROM product_transaction WHERE product_transaction.transactionId = :id")
    public abstract Flowable<ProductTransactionData>getTransactionById(long id);
    @Transaction
    @Query("SELECT * FROM product_transaction WHERE productId = :id")
    public abstract Single<List<ProductTransactionData>> getTransactionsByProductId(long id);
    @Query("DELETE FROM product_transaction")
    public abstract Completable deleteAllTransaction();
}
