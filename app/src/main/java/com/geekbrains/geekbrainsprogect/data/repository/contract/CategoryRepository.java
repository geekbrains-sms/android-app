package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;


import java.util.List;

import io.reactivex.Observable;

public interface CategoryRepository {
    Observable<List<Category>> getAllCategory();
    Observable<Category> getCategoryById(long id);
    Observable<List<Category>>deleteCategoryById(long id);
    Observable<List<Category>> addCategory(Category category);
}
