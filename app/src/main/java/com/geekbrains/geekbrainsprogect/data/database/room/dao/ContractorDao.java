package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ContractorDao extends BaseDao<Contractor> {
    @Query("SELECT * FROM contractor")
    Flowable<List<Contractor>> getAllContractor();
    @Query("SELECT * FROM contractor WHERE contractor.id = :id")
    Flowable<Contractor>getContractorById(long id);
    @Query("DELETE FROM contractor")
    Completable deleteAllContractor();
}
