package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public abstract class ContractorDao extends BaseDao<Contractor> {
    @Query("SELECT * FROM contractor")
    public abstract Flowable<List<Contractor>> getAllContractor();
    @Query("SELECT * FROM contractor WHERE contractor.contractorId = :id")
    public abstract List<Contractor>getContractorById(long id);
    @Query("DELETE FROM contractor")
    public abstract Completable deleteAllContractor();
}
