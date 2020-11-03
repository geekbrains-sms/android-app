package com.geekbrains.geekbrainsprogect.domain.interactor.contract;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ContractorInteractor {
    Completable saveContractorsFromServerToDb();
    Flowable<List<Contractor>> getAllContractorList();
    Completable addContractor(Contractor contractor);
    Completable editContractor(Contractor contractor);
    Completable deleteContractor(Contractor contractor);
}
