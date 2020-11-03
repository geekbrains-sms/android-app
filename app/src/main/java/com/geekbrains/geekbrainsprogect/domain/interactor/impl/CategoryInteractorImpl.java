package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.CategoryInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class CategoryInteractorImpl implements CategoryInteractor {
    CategoryRepository categoryRepository;
    @Inject
    public CategoryInteractorImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Completable saveFromServerToDb() {
        return categoryRepository.getCategoriesByServer();
    }

    @Override
    public Flowable<List<Category>> getCategoryFromDb() {
        return categoryRepository.getAllCategoriesFromBD();
    }

    @Override
    public Completable deleteCategory(Category category) {
        return categoryRepository.deleteCategory(category);
    }

    @Override
    public Completable addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }
}
