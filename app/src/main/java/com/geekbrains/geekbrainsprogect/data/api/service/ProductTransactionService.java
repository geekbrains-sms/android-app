package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.response.ProductTransactionResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductTransactionService {
    @GET("/api/v1/transactions/supply")
    Observable<ProductTransactionResponse> getAllSupplyProductTransactions();
    @GET("/api/v1/transactions/shipment")
    Observable<ProductTransactionResponse>getAllShipmentProductTransactions();
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/supply")
    Observable<ProductTransactionResponse>addSupplyTransactions(@Body ProductTransaction productTransaction);
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/shipment")
    Observable<ProductTransactionResponse>addShipmentTransactions(@Body ProductTransaction productTransaction);
    @GET("/api/v1/transactions/product/{id}")
    Observable<ProductTransactionResponse>getProductTransactionById(@Path("id")long id);
}
