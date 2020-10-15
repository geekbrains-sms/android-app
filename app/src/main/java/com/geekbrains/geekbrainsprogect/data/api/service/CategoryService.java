package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.response.CategoryResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("/api/v1/categories")
    Observable<CategoryResponse> getCategoryList();
    @GET("/api/v1/categories/{id}")
    Observable<Category> getCategoryById(@Path("id")long id);
    @DELETE("/api/v1/categories/{id}")
    Observable<CategoryResponse>deleteCategoryById(@Path("id")long id);
    @POST("/api/v1/categories")
    Observable<Category>addCategory(@Body Category category);
    @PUT("/api/v1/categories")
    Observable<CategoryResponse> editCategory(@Body Category category);
}
