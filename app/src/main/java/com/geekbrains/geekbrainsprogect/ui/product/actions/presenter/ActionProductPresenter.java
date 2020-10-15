package com.geekbrains.geekbrainsprogect.ui.product.actions.presenter;

import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.ui.product.actions.view.ActionProductView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class ActionProductPresenter extends MvpPresenter<ActionProductView> {
    public ActionProductPresenter()
    {
        loadUserActions();
    }

    private void loadUserActions() {
        Single<Response<List<UserAction>>> single = AppData.getApiHelper().getAllUserActions();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(actionResponse ->{
            if(actionResponse.isSuccessful())
            {
                getViewState().setDataToAdapter(actionResponse.body());
            }
            else
            {
                getViewState().setAlertDialog(actionResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().setAlertDialog(throwable.getMessage());
        });
    }
}
