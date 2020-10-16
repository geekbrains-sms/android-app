package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Completable;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(T...objects);
    @Delete
    void delete(T...objects);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T...objects);
}
