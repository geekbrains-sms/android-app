package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.model.response.FundResponse;
import com.geekbrains.geekbrainsprogect.data.repository.contract.FundRepository;

import io.reactivex.Observable;

public class FundRepositoryImpl implements FundRepository {


    @Override
    public Observable<FundResponse> getAllFunds() {
        return null;
    }

    @Override
    public Observable<Fund> getFundsByProductId(long id) {
        return null;
    }
}
