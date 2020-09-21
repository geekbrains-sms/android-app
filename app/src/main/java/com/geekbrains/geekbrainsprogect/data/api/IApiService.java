package com.geekbrains.geekbrainsprogect.data.api;

import com.geekbrains.geekbrainsprogect.data.User;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiService {
    @GET("rest/users/all")
    Single<List<User>> getAllUsers();

    @POST("warehouse/authenticate")
    Single<Response<ResponseBody>>authUser(@Query("login") String login, @Query("password") String password);

    @GET("rest/user/{username}")
    Single<User> getUser(@Path("username") String userName);

    @POST("rest/users")
    Single<String>postUser(@Query("username") String loginUser, @Query("password")String passwordUser);

    @DELETE("rest/users")
    Single<String>deleteUser(@Query("username") String loginUser);

    @PUT("rest/users")
    Single<String>editUser(@Query("username") String loginUser, @Query("password") String passwordUser, @Query("confirmPassword") String passwordConfirm,
                           @Query("fullname") String fullname, @Query("email") String email, @Query("phone") String phone, @Query("roleNames") String[]roleNames);
    @POST("rest/users")
    Single<String>registerUser(@Query("username") String loginUser, @Query("password") String passwordUser, @Query("confirmPassword") String passwordConfirm,
                               @Query("fullname") String fullname, @Query("email") String email, @Query("phone") String phone, @Query("roleNames") String[]roleNames);
}
