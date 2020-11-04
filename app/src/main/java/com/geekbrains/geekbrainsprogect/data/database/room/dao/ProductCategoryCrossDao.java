package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductCategoryCrossRef;

import io.reactivex.Completable;

@Dao
public abstract class ProductCategoryCrossDao extends BaseDao<ProductCategoryCrossRef> {
    @Query("DELETE FROM product_category_cross WHERE productId = :id")
    public abstract void deleteByProduct(long id);
    @Query("DELETE FROM product_category_cross WHERE categoryId = :id")
    abstract Completable deleteByCategory(long id);
}
