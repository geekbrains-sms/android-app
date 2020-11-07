package com.geekbrains.geekbrainsprogect.ui.contractors.list.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.view.ContractorsListView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ContractorsListPresenter extends MvpPresenter<ContractorsListView> {
    ContractorInteractor contractorInteractor;
    Disposable disposable;
    @Inject
    public ContractorsListPresenter(ContractorInteractor contractorInteractor) {
        this.contractorInteractor = contractorInteractor;
        getContractorListFromServer();
    }

    private void getContractorListFromServer() {
        Disposable disposable = contractorInteractor.saveContractorsFromServerToDb()
                .subscribeOn(Schedulers.io())
                .doFinally(this::getContractorList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{}, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }


    public void getContractorList()
    {
       disposable = contractorInteractor.getAllContractorList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contractors -> {

                getViewState().setDataToAdapter(contractors);
        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void addContractor(Contractor contractor) {
        Disposable disposable = contractorInteractor.addContractor(contractor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.contractors_added);
                }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void editContractor(Contractor contractorEdit) {
        Disposable disposable = contractorInteractor.editContractor(contractorEdit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.contractors_edit);
        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void deleteContractor(Contractor contractor) {
        Disposable disposable = contractorInteractor.deleteContractor(contractor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.contractors_delete);
        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
