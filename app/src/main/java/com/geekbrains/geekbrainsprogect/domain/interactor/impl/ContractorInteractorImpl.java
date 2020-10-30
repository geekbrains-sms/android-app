package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ContractorInteractorImpl implements ContractorInteractor {
    ContractorRepository contractorRepository;
    @Inject
    public ContractorInteractorImpl(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Completable saveContractorsFromServerToDb() {
        return contractorRepository.saveContractorFromServerToDB();
    }

    @Override
    public Flowable<List<Contractor>> getAllContractorList() {
        return contractorRepository.getAllContractors();
    }

    @Override
    public Completable addContractor(Contractor contractor) {
        return contractorRepository.addContractor(contractor)
                .ignoreElements();
    }

    @Override
    public Completable editContractor(Contractor contractor) {
        return contractorRepository.editContractor(contractor)
                .ignoreElements();
    }
}
