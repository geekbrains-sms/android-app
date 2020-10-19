package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
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
    Observable<List<ProductTransactionDTO>> getAllSupplyProductTransactions();
    @GET("/api/v1/transactions/shipment")
    Observable<List<ProductTransactionDTO>>getAllShipmentProductTransactions();
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/supply")
    Observable<List<ProductTransactionDTO>>addSupplyTransactions(@Body ProductTransaction productTransaction);
    @Headers("Content-Type: application/json")
    @POST("/api/v1/transactions/shipment")
    Observable<List<ProductTransactionDTO>>addShipmentTransactions(@Body ProductTransaction productTransaction);
    @GET("/api/v1/transactions/product/{id}")
    Observable<List<ProductTransactionDTO>>getProductTransactionById(@Path("id")long id);
}
