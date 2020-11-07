package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.UnitService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UnitDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UnitRepositoryImpl implements UnitRepository {
    UnitDao unitDao;
    UnitService unitService;

    @Inject
    public UnitRepositoryImpl(UnitDao unitDao, UnitService unitService) {
        this.unitDao = unitDao;
        this.unitService = unitService;
    }

    @Override
    public Completable getAllUnitsFromServer() {
        return unitService.getAllUnits()
                .flatMapCompletable(x -> Completable.fromRunnable(() ->
                {
                    unitDao.deleteAllUnits();
                    unitDao.insertAll(x);
                }));
    }

    @Override
    public Flowable<List<Unit>> getAllUnitFromBD() {
        return unitDao.getAllUnits();
    }

    @Override
    public Completable addUnitToDB(ProductWithCategory productWithCategory) {
        return Completable.fromRunnable(() -> unitDao.update(productWithCategory.getUnit()));
    }
}
