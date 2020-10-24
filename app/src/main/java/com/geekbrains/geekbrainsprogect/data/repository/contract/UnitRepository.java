package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import java.util.List;

import io.reactivex.Observable;

public interface UnitRepository {
    Observable<List<Unit>> getAllUnits();
}
