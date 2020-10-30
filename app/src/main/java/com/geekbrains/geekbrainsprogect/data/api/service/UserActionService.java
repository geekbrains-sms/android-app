package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserActionService {
    @GET("/api/v1/actions")
    Observable<List<UserAction>> getAllUserActions();
    @GET("/api/v1/actions/{id}")
    Observable<List<UserAction>>getUserActionByProductId(@Path("id")long id);
    @GET("/api/v1/actions/{author}")
    Observable<List<UserAction>>getUserActionByAuthor(@Path("author")String author);
    @GET("/api/v1/actions/{data}")
    Observable<List<UserAction>>getUserActionByData(@Path("data")String data);
}
