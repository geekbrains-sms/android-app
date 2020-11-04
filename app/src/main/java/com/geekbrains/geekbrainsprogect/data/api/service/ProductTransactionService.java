package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.model.ProductTransactionDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductTransactionService {
    @GET("/api/v1/transactions/supply")
    Observable<List<ProductTransactionDTO>> getAllSupplyProductTransactions();
    @GET("/api/v1/transactions/shipment")
    Observable<List<ProductTransactionDTO>>getAllShipmentProductTransactions();
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/supply")
    Observable<ProductTransactionDTO>addSupplyTransactions(@Body ProductTransactionDTO productTransaction);
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/shipment")
    Observable<ProductTransactionDTO>addShipmentTransactions(@Body ProductTransactionDTO productTransaction);
    @GET("/api/v1/transactions/product/{id}")
    Observable<List<ProductTransactionDTO>>getProductTransactionById(@Path("id")long id);
}
