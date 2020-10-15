package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.response.ContractorResponse;

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

public interface ContractorService {
    @GET("/api/v1/contractors")
    Observable<ContractorResponse> getAllContractors();
    @GET("/api/v1/contractors/providers/{productId}")
    Observable<ContractorResponse>getProvidersByProductId(@Path("productId")long id);
    @POST("/api/v1/contractors")
    Observable<Contractor>addContractor(@Body Contractor contractor);
    @DELETE("/api/v1/contractors/{id}")
    Observable<ResponseBody>deleteContractorById(@Path("id")long id);
    @PUT("/api/v1/contractors")
    Observable<ResponseBody>editContractor(@Body Contractor contractor);
}
