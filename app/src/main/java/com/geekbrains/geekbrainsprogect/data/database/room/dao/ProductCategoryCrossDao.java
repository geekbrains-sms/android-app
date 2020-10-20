package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryCrossRef;

import io.reactivex.Completable;

@Dao
public interface ProductCategoryCrossDao extends BaseDao<ProductCategoryCrossRef> {
    @Query("DELETE FROM product_category_cross WHERE productId = :id")
    Completable deleteByProduct(long id);
    @Query("DELETE FROM product_category_cross WHERE categoryId = :id")
    Completable deleteByCategory(long id);
}
