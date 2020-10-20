package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;
import androidx.room.Transaction;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ProductDao extends BaseDao<Product> {
    @Transaction
    @Query("SELECT * FROM product")
    Flowable<List<ProductWithCategory>> getAllProduct();
    @Query("SELECT * FROM product WHERE product.id = :id")
    Flowable<ProductWithCategory>getProductById(long id);
    @Query("DELETE FROM product")
    Completable deleteAllProduct();
    @Query("DELETE * FROM product WHERE id = :id")
    Completable deleteProductById(long id);

}
