package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;

import java.util.List;

import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiService {
    @GET("auth/users/all")
    Single<List<User>> getAllUsers();

    @POST("auth/users/add")
    Single<String>postUser(@Query("login") String loginUser, @Query("password")String passwordUser);
}
