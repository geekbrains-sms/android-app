package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductContractorCrossDao;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.cross.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ContractorRepositoryImpl implements ContractorRepository {
    ContractorService contractorService;
    ContractorDao contractorDao;
    ProductContractorCrossDao productContractorCrossDao;
    @Inject
    public ContractorRepositoryImpl(ContractorService contractorService, ContractorDao contractorDao, ProductContractorCrossDao productContractorCrossDao) {
        this.contractorService = contractorService;
        this.contractorDao = contractorDao;
        this.productContractorCrossDao = productContractorCrossDao;
    }



    @Override
    public Flowable<List<Contractor>> getAllContractors() {
        return contractorDao.getAllContractor();
    }

    @Override
    public Completable saveContractorFromServerToDB() {
        return contractorService.getAllContractors()
                .flatMapCompletable(x -> Completable.fromRunnable(() -> contractorDao.insertAll(x)));
    }

    @Override
    public Observable<Contractor> addContractor(Contractor contractor) {
        return null;
    }

    @Override
    public Completable deleteContractorById(long id) {
        return contractorService.deleteContractorById(id)
                .flatMapCompletable(x -> contractorDao.deleteById(id));

    }

    @Override
    public Observable<ResponseBody> editContractor(Contractor contractor) {
        return null;
    }

    @Override
    public Completable addContractorsCross(long productID) {
        return contractorService.getProvidersByProductId(productID)
                .doOnNext(x -> productContractorCrossDao.deleteByProduct(productID))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x ->
                        Completable.fromRunnable(() -> {
                            contractorDao.insert(x);
                            productContractorCrossDao.insert(new ProductContractorCrossRef(productID, x.getId()));
                        }));
    }
}
