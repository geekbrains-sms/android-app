package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product_list.model.Category;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiService {

    @GET("/api/v1/products")
    Single<List<Product>> getProductList();
    @POST("/api/v1/products")
    Single<Response<String>>addProduct(@Body Product product);
    @DELETE("/api/v1/products/{id}")
    Single<Response<String>>deleteProductById(@Path("id")long id);
    @DELETE("/api/v1/products")
    Single<Response<String>>deleteAllProduct();
    @PUT("/api/v1/products")
    Single<Response<ResponseBody>>editProduct(@Body Product product);

    @GET("/api/v1/categories")
    Single<Response<List<Category>>>getCategoryList();
    @GET("/api/v1/categories/{id}")
    Single<Response<Category>>getCategoryById(@Path("id")long id);
    @DELETE("/api/v1/categories/{id}")
    Single<Response<String>>deleteCategoryById(@Path("id")long id);
    @POST("/api/v1/categories")
    Single<Response<List<String>>>addCategory(@Body Category category);
    @PUT("/api/v1/categories")
    Single<Response<List<String>>>editCategory(@Body Category category);





}
