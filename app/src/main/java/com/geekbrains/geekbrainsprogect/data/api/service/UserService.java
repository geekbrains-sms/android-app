package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.dto.UserDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("/api/v1/users/actual")
    Observable<List<UserDTO>> getActualUsers();
    @GET("/api/v1/users/{id}")
    Observable<UserDTO>getUserById(@Path("id")long id);
    @PUT("/api/v1/users/{id}")
    Observable<ResponseBody>editUser(@Path("id")long id, @Body UserDTO user);
    @Headers("Content-Type: application/json")
    @POST("/api/v1/users")
    Observable<UserDTO>addUser(@Body UserDTO user);
    @DELETE("/api/v1/users/{id}")
    Observable<ResponseBody>deleteUser(@Path("id")long id);
}
