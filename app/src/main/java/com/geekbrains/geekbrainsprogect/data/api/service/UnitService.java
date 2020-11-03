package com.geekbrains.geekbrainsprogect.data.api.service;

import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UnitService {
    @GET("/api/v1/units")
    Observable<List<Unit>> getAllUnits();
}
