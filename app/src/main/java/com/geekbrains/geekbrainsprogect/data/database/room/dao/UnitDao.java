package com.geekbrains.geekbrainsprogect.data.database.room.dao;
import androidx.room.Dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
@Dao
public abstract class UnitDao extends BaseDao<Unit> {
    @Query("SELECT * FROM unit")
    public abstract Flowable<List<Unit>>getAllUnits();
    @Query("SELECT * FROM unit WHERE unitId = :id")
    public abstract Flowable<Unit> getUnitById(long id);
    @Query("DELETE FROM unit")
    public abstract void deleteAllUnits();

}
