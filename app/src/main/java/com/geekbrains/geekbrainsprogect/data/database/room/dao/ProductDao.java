package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public abstract class ProductDao extends BaseDao<Product> {
    @Transaction
    @Query("SELECT * FROM product")
    public abstract Flowable<List<ProductWithCategory>> getAllProduct();
    @Transaction
    @Query("SELECT * FROM product WHERE product.productId = :id")
    abstract Flowable<ProductWithCategory>getProductById(long id);
    @Query("DELETE FROM product")
    public abstract void deleteAllProduct();
    @Query("DELETE FROM product WHERE productId = :id")
    public abstract void deleteProductById(long id);

}
