package com.geekbrains.geekbrainsprogect.data.database.room.dao;

import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface FundDao extends BaseDao<Fund> {
    @Query("SELECT * FROM fund")
    Flowable<List<Fund>>getAllFund();
    @Query("SELECT * FROM fund WHERE fund.product_id = :id")
    Flowable<Fund>getFundByProductId(long id);
    @Query("DELETE FROM fund")
    Completable deleteAllFund();

}
