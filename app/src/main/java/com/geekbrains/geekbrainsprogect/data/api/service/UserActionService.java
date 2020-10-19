package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.api.dto.UserActionDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.model.response.UserActionResponse;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserActionService {
    @GET("/api/v1/actions")
    Observable<List<UserActionDTO>> getAllUserActions();
    @GET("/api/v1/actions/{id}")
    Observable<List<UserActionDTO>>getUserActionByProductId(@Path("id")long id);
    @GET("/api/v1/actions/{author}")
    Observable<List<UserActionDTO>>getUserActionByAuthor(@Path("author")String author);
    @GET("/api/v1/actions/{data}")
    Observable<List<UserActionDTO>>getUserActionByData(@Path("data")String data);
}
