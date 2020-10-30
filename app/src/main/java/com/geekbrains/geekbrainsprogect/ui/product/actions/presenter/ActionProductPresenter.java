package com.geekbrains.geekbrainsprogect.ui.product.actions.presenter;

import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserActionInteractor;
import com.geekbrains.geekbrainsprogect.ui.product.actions.view.ActionProductView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class ActionProductPresenter extends MvpPresenter<ActionProductView> {
    UserActionInteractor userActionInteractor;
    @Inject
    public ActionProductPresenter(UserActionInteractor userActionInteractor)
    {
        this.userActionInteractor = userActionInteractor;
        loadUserActions();
        saveDataToDB();
    }

    private void saveDataToDB() {
        Disposable disposable = userActionInteractor.saveUserActionFromServerToBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    private void loadUserActions() {
        Disposable disposable = userActionInteractor.getUserActionsFromBD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actionResponse ->{

                getViewState().setDataToAdapter(actionResponse);

        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }
}
