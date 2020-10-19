package com.geekbrains.geekbrainsprogect.data.api.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UnitService {
    @GET("/api/v1/units")
    Observable<List<UnitDTO>> getAllUnits();
}
