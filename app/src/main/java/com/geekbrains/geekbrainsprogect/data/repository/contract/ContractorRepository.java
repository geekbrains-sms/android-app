package com.geekbrains.geekbrainsprogect.data.repository.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.response.ContractorResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ContractorRepository {
    Observable<ContractorResponse> getAllContractors();
    Observable<ContractorResponse>getProvidersByProductId(long id);
    Observable<Contractor>addContractor(Contractor contractor);
    Observable<ResponseBody>deleteContractorById(long id);
    Observable<ResponseBody>editContractor(Contractor contractor);
}
