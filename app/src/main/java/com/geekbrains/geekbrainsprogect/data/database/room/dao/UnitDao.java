package com.geekbrains.geekbrainsprogect.data.database.room.dao;
import androidx.room.Dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import io.reactivex.Flowable;
import io.reactivex.Single;
@Dao
public abstract class UnitDao extends BaseDao<Unit> {
    @Query("SELECT * FROM unit")
    abstract Flowable<Unit>getAllUnits();
    @Query("SELECT * FROM unit WHERE id = :id")
    public abstract Flowable<Unit> getUnitById(long id);

}
