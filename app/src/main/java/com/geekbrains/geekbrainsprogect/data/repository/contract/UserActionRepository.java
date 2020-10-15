package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.response.UserActionResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserActionRepository {
    Observable<UserActionResponse> getAllUserActions();
    Observable<UserActionResponse>getUserActionByProductId(long id);
    Observable<UserActionResponse>getUserActionByAuthor(String author);
    Observable<UserActionResponse>getUserActionByData(String data);
}
