package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import android.database.DatabaseErrorHandler;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;

import io.reactivex.Completable;

@Dao
public abstract class ProductContractorCrossDao extends BaseDao<ProductContractorCrossRef> {
    @Query("DELETE FROM product_contractor_cross WHERE productId = :id")
    public abstract Completable deleteByProduct(long id);
    @Query("DELETE FROM product_contractor_cross WHERE contractorId = :id")
    abstract Completable deleteByContractor(long id);
}
