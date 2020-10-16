package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ProductDao extends BaseDao<ProductDao> {
    @Query("SELECT * FROM product")
    Flowable<List<Product>> getAllProduct();
    @Query("SELECT * FROM product WHERE product.id = :id")
    Flowable<Product>getProductById(long id);
    @Query("DELETE FROM product")
    Completable deleteAllProduct();

}
