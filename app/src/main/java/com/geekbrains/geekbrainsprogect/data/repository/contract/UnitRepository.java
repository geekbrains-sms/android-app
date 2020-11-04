package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UnitRepository {
    Completable getAllUnitsFromServer();
    Flowable<List<Unit>>getAllUnitFromBD();
    Completable addUnitToDB(ProductWithCategory productWithCategory);
}
