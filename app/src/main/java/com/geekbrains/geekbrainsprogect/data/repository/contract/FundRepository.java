package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.response.FundResponse;

import io.reactivex.Observable;

public interface FundRepository {
    Observable<FundResponse> getAllFunds();
    Observable<Fund>getFundsByProductId(long id);
}
