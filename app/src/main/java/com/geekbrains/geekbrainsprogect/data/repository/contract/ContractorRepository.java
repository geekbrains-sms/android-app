package com.geekbrains.geekbrainsprogect.data.repository.contract;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface ContractorRepository {
    Flowable<List<Contractor>> getAllContractors();
    Completable saveContractorFromServerToDB();
    Observable<List<Contractor>>getProvidersByProductId(long id);
    Observable<Contractor>addContractor(Contractor contractor);
    Completable deleteContractorById(long id);
    Observable<ResponseBody>editContractor(Contractor contractor);
    Completable addContractorsCross(long productID);
}
