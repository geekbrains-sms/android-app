package com.geekbrains.geekbrainsprogect.data.repository.contract;


import android.database.Observable;

import com.geekbrains.geekbrainsprogect.data.api.dto.FundDTO;

public interface FundRepository {
    Observable<FundDTO> getAllFunds();
    Observable<FundDTO>getFundsByProductId(long id);
}
