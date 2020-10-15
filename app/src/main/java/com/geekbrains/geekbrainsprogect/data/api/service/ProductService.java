package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.response.ProductResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @GET("/api/v1/products")
    Observable<ProductResponse> getProductList();
    @POST("/api/v1/products")
    Observable<Product>addProduct(@Body Product product);
    @DELETE("/api/v1/products/{id}")
    Observable<String>deleteProductById(@Path("id")long id);
    @DELETE("/api/v1/products")
    Observable<String>deleteAllProduct();
    @PUT("/api/v1/products")
    Observable<ResponseBody>editProduct(@Body Product product);
}
