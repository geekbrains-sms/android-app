package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.response.CategoryResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CategoryRepository {
    Observable<CategoryResponse> getAllCategory();
    Observable<Category> getCategoryById(long id);
    Observable<CategoryResponse>deleteCategoryById(long id);
    Observable<CategoryResponse> addCategory(Category category);
}
