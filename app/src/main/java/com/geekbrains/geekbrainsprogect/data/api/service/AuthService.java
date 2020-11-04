package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.model.AuthDTO;
import com.geekbrains.geekbrainsprogect.data.api.model.AuthToken;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth")
    Single<Response<AuthToken>>authUser(@Body AuthDTO jsonObject);
}
