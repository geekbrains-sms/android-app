package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.CategoryService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class CategoryRepositoryImpl implements CategoryRepository {
    CategoryDao categoryDao;
    CategoryService categoryService;

    public CategoryRepositoryImpl(CategoryDao categoryDao, CategoryService categoryService) {
        this.categoryDao = categoryDao;
        this.categoryService = categoryService;
    }

    @Override
    public Flowable<List<Category>> getAllCategoriesFromBD() {
        return categoryDao.getAllCategory();
    }

    @Override
    public Completable getCategoriesByServer() {
        return categoryService.getCategoryList()
                .doOnNext(x -> {
                    categoryDao.deleteAllCategory();
                    categoryDao.insertAll(x);
                })
                .ignoreElements();
    }

    @Override
    public Completable deleteCategory(Category category) {
        return categoryService.deleteCategoryById(category.id)
                .doOnNext(x -> categoryDao.delete(category))
                .ignoreElements();
    }

    @Override
    public Completable addCategory(Category category) {
        return categoryService.addCategory(category)
                .doOnNext(x -> categoryDao.insert(category))
                .ignoreElements();
    }
}
