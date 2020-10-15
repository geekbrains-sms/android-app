package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Fund;
import com.geekbrains.geekbrainsprogect.data.model.response.FundResponse;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FundService {
    @GET("/api/v1/funds")
    Observable<FundResponse> getAllFunds();
    @GET("/api/v1/funds/product/{var}")
    Observable<Fund>getFundsByProductId(@Path("var")long id);
}
