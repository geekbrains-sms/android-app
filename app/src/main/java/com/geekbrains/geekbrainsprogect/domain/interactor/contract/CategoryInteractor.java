package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface CategoryInteractor {
    Completable saveFromServerToDb();
    Flowable<List<Category>>getCategoryFromDb();
    Completable deleteCategory(Category category);
    Completable addCategory(Category category);
}
