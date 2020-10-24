package com.geekbrains.geekbrainsprogect.data.database.room.dao;
import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public abstract class CategoryDao extends BaseDao<Category> {
    @Query("SELECT * FROM category")
    abstract Flowable<List<Category>> getAllCategory();
    @Query("SELECT * FROM category WHERE category.categoryId = :id")
    abstract Flowable<Category>getCategoryById(long id);
//    @Query("SELECT * FROM category INNER JOIN product_category_join ON category.id = product_category_join.category_id WHERE product_category_join.product_id = :id")
//    Flowable<List<Category>> getCategoriesByProductId(long id);
    @Query("DELETE FROM category")
    abstract Completable deleteAllCategory();
}
