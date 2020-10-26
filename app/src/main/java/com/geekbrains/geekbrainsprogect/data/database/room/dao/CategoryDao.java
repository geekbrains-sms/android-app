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
    public abstract Flowable<List<Category>> getAllCategory();
    @Query("SELECT * FROM category WHERE category.categoryId = :id")
    public abstract Flowable<Category>getCategoryById(long id);
    @Query("DELETE FROM category")
    public abstract void deleteAllCategory();
}
