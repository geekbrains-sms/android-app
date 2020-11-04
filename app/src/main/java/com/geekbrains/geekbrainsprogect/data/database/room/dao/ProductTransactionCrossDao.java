package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductTransactionCrossRef;

import io.reactivex.Completable;

@Dao
public abstract class ProductTransactionCrossDao extends BaseDao<ProductTransactionCrossRef> {
    @Query("DELETE FROM product_transaction_cross WHERE productId = :id")
    public abstract void deleteByProduct(long id);
    @Query("DELETE FROM product_transaction_cross WHERE transactionId = :id")
    public abstract Completable deleteByTransaction(long id);
    @Query("DELETE FROM product_transaction_cross")
    public abstract void deleteAll();
}
