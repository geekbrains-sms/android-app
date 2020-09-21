package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthToken;

import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IAuthService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth")
    Single<Response<AuthToken>>authUser(@Body UserRequest jsonObject);
}
