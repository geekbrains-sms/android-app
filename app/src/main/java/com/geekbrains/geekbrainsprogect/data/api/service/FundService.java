package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.model.FundDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FundService {
    @GET("/api/v1/funds")
    Observable<List<FundDTO>> getAllFunds();
    @GET("/api/v1/funds/product/{var}")
    Observable<FundDTO> getFundsByProductId(@Path("var")long id);
}
