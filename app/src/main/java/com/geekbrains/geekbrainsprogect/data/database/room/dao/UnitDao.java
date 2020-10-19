package com.geekbrains.geekbrainsprogect.data.database.room.dao;
import androidx.room.Query;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import io.reactivex.Flowable;

public interface UnitDao extends BaseDao<Unit> {
    @Query("SELECT * FROM unit")
    Flowable<Unit>getAllUnits();
}
