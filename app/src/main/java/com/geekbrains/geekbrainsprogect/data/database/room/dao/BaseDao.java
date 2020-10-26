package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;


public abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(T...objects);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<T>list);
    public abstract Completable deleteAll(List<T>list);
    @Delete
    public abstract void delete(T...objects);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract void update(T...objects);
}
