package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.response.UnitsResponse;

import io.reactivex.Observable;

public interface UnitRepository {
    Observable<UnitsResponse> getAllUnits();
}
