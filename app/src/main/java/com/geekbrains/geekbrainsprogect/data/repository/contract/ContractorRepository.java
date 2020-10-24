package com.geekbrains.geekbrainsprogect.data.repository.contract;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ContractorRepository {
    Observable<List<Contractor>> getAllContractors();
    Observable<List<Contractor>>getProvidersByProductId(long id);
    Observable<Contractor>addContractor(Contractor contractor);
    Observable<ResponseBody>deleteContractorById(long id);
    Observable<ResponseBody>editContractor(Contractor contractor);
}
