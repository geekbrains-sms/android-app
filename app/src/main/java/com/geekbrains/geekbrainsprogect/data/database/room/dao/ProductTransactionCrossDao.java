package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;

import io.reactivex.Completable;

@Dao
public interface ProductTransactionCrossDao extends BaseDao<ProductTransactionCrossRef> {
    @Query("DELETE FROM product_transaction_cross WHERE productId = :id")
    Completable deleteByProduct(long id);
    @Query("DELETE FROM product_transaction_cross WHERE transactionId = :id")
    Completable deleteByTransaction(long id);
}
