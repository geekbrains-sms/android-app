package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import android.database.DatabaseErrorHandler;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;

import io.reactivex.Completable;

@Dao
public interface ProductContractorCrossDao extends BaseDao<ProductContractorCrossRef> {
    @Query("DELETE FROM product_contractor_cross WHERE productId = :id")
    Completable deleteByProduct(long id);
    @Query("DELETE FROM product_contractor_cross WHERE contractorId = :id")
    Completable deleteByContractor(long id);
}
