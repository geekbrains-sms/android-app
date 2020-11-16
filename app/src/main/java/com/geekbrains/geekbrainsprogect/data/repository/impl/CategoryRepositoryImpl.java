package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.CategoryService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryCrossDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class CategoryRepositoryImpl implements CategoryRepository {
    CategoryDao categoryDao;
    CategoryService categoryService;
    ProductCategoryCrossDao productCategoryCrossDao;
    @Inject
    public CategoryRepositoryImpl(CategoryDao categoryDao, CategoryService categoryService, ProductCategoryCrossDao productCategoryCrossDao) {
        this.categoryDao = categoryDao;
        this.categoryService = categoryService;
        this.productCategoryCrossDao = productCategoryCrossDao;
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
        return categoryService.deleteCategoryById(category.getId())
                .doOnNext(x -> categoryDao.delete(category))
                .ignoreElements();
    }

    @Override
    public Completable addCategory(Category category) {
        return categoryService.addCategory(category)
                .doOnNext(x -> categoryDao.insert(x))
                .ignoreElements();
    }

    @Override
    public Completable addCategoryCross(ProductWithCategory productWithCategory) {
        return Completable.fromRunnable(()->{
            productCategoryCrossDao.deleteByProduct(productWithCategory.getId());
            for(Category category: productWithCategory.getCategoryList())
            {
                categoryDao.insert(category);
                productCategoryCrossDao.insert(new ProductCategoryCrossRef(productWithCategory.getId(), category.getId()));
            }
        });
    }
}
