package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;


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
    Completable addCategoryCross(ProductWithCategory productWithCategory);
}
