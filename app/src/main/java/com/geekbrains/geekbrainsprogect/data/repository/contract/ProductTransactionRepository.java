package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.response.ProductTransactionResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductTransactionRepository {
    Observable<ProductTransactionResponse> getAllSupplyProductTransactions();
    Observable<ProductTransactionResponse>getAllShipmentProductTransactions();
    Observable<ProductTransactionResponse>addSupplyTransactions(ProductTransaction productTransaction);
    Observable<ProductTransactionResponse>addShipmentTransactions(ProductTransaction productTransaction);
    Observable<ProductTransactionResponse>getProductTransactionById(long id);
}
