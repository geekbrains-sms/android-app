package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("/api/v1/categories")
    Observable<List<Category>> getCategoryList();
    @GET("/api/v1/categories/{id}")
    Observable<Category> getCategoryById(@Path("id")long id);
    @DELETE("/api/v1/categories/{id}")
    Observable<List<Category>>deleteCategoryById(@Path("id")long id);
    @POST("/api/v1/categories")
    Observable<Category>addCategory(@Body Category category);
    @PUT("/api/v1/categories")
    Observable<List<Category>> editCategory(@Body Category category);
}
