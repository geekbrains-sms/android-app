package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.response.UserResponse;

import java.util.List;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("/api/v1/users/actual")
    Single<UserResponse> getActualUsers();
    @GET("/api/v1/users/{id}")
    Single<User>getUserById(@Path("id")long id);
    @PUT("/api/v1/users/{id}")
    Single<ResponseBody>editUser(@Path("id")long id, @Body User user);
    @POST("/api/v1/users")
    Single<Response<User>>addUser(@Body User user);
    @DELETE("/api/v1/users/{id}")
    Single<ResponseBody>deleteUser(@Path("id")long id);
}
