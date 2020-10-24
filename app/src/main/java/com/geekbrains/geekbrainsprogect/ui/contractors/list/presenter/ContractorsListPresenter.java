package com.geekbrains.geekbrainsprogect.ui.contractors.list.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.view.ContractorsListView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import okhttp3.ResponseBody;
import retrofit2.Response;

@InjectViewState
public class ContractorsListPresenter extends MvpPresenter<ContractorsListView> {

    public ContractorsListPresenter()
    {
        getContractorList();
    }

    public void getContractorList()
    {
//        Single<Response<List<Contractor>>> single = AppData.getApiHelper().getAllContractors();
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
//            if(requestMsg.isSuccessful())
//            {
//                AppData.setContractorList(requestMsg.body());
//                getViewState().setDataToAdapter(AppData.getContractorList());
//            }
//            else
//            {
//                getViewState().showAlertDialog(requestMsg.errorBody().string());
//            }
//
//        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void addContractor(Contractor contractor) {
//        Single<Response<Contractor>> single = AppData.getApiHelper().addContractor(contractor);
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
//            if(requestMsg.isSuccessful())
//            {
//                AppData.getContractorList().add(requestMsg.body());
//                getViewState().setDataToAdapter(AppData.getContractorList());
//                getViewState().showToast(R.string.contractors_added);
//            }
//            else
//            {
//                getViewState().showAlertDialog(requestMsg.errorBody().string());
//            }
//
//        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void editContractor(Contractor contractorEdit, Contractor old) {
//        Single<Response<ResponseBody>> single = AppData.getApiHelper().editContractor(contractorEdit);
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
//            if(requestMsg.isSuccessful())
//            {
//                AppData.updateContractors(old, contractorEdit);
//                getViewState().setDataToAdapter(AppData.getContractorList());
//                getViewState().showToast(R.string.contractors_edit);
//            }
//            else
//            {
//                getViewState().showAlertDialog(requestMsg.errorBody().string());
//            }
//
//        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void deleteContractor(Contractor contractor) {
//        Single<Response<ResponseBody>> single = AppData.getApiHelper().deleteContractorById(contractor.getId());
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
//            if(requestMsg.isSuccessful())
//            {
//                AppData.getContractorList().remove(contractor);
//                getViewState().setDataToAdapter(AppData.getContractorList());
//                getViewState().showToast(R.string.contractors_delete);
//            }
//            else
//            {
//                getViewState().showAlertDialog(requestMsg.errorBody().string());
//            }
//
//        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }
}
