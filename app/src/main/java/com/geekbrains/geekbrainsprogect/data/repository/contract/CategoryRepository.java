package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface CategoryRepository {
    Flowable<List<Category>>getAllCategoriesFromBD();
    Completable getCategoriesByServer();
    Completable deleteCategory(Category category);
    Completable addCategory(Category category);
}
