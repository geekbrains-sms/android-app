package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;

import java.util.List;

import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiService {
    @GET("rest/users/all")
    Single<List<User>> getAllUsers();

    @GET("rest/user/{username}")
    Single<User> getUser(@Path("username") String userName);

    @POST("rest/users")
    Single<String>postUser(@Query("username") String loginUser, @Query("password")String passwordUser);
}
