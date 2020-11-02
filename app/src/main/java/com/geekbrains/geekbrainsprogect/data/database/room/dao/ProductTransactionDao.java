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
    public abstract Flowable<List<ProductTransactionData>> getAllTransaction();
    @Query("SELECT * FROM product_transaction WHERE product_transaction.transactionId = :id")
    public abstract Flowable<ProductTransactionData>getTransactionById(long id);
    @Query("SELECT * FROM product_transaction WHERE productId = :id")
    public abstract Flowable<List<ProductTransactionData>>getTransactionsByProductId(long id);
    @Query("DELETE FROM product_transaction")
    public abstract Completable deleteAllTransaction();
}
