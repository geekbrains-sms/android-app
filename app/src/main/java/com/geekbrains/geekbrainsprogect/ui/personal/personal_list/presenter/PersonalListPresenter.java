package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import okhttp3.ResponseBody;
import retrofit2.Response;

@InjectViewState
public class PersonalListPresenter extends MvpPresenter<PersonalListView> {
    private static String TAG = "PersonalListPresenter";
    UserInteractor userInteractor;

    public PersonalListPresenter (UserInteractor userInteractor)
    {
        this.userInteractor = userInteractor;
        loadUserFromDB();
        loadUserListFromServer();

    }

    private void loadUserFromDB() {
        Disposable disposable = userInteractor.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userList -> {
                    getViewState().setDataToAdapter(userList);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public void loadUserListFromServer() {
        Disposable disposable = userInteractor.saveUserFromServerToDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }

    public void deleteUser(UserModel user)
    {
        Disposable disposable = userInteractor.deleteUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.user_deleted);
        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void editUser(UserModel user)
    {
        Disposable disposable = userInteractor.editUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.user_edited);
        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void addUser(UserModel user) {
        Disposable disposable = userInteractor.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.user_added);

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void createPersonalDialog(UserModel item) {
        Disposable disposable = userInteractor.getAllRolesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roles -> {
                    getViewState().showEditDialog(item, roles);
                }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void createNewUserDialog() {
        Disposable disposable = userInteractor.getAllRolesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roles -> {
                    getViewState().showAddPersonalDialog(roles);
                }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));

    }
}
