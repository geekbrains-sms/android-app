package com.geekbrains.geekbrainsprogect.data.database.room.dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface UnitDao extends BaseDao<Unit> {
    @Query("SELECT * FROM unit")
    Flowable<Unit>getAllUnits();
    @Query("SELECT * FROM unit WHERE id = :id")
    Flowable<Unit> getUnitById(long id);

}
