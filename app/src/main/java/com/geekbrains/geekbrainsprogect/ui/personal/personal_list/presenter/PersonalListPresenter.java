package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListView;

import java.util.ArrayList;
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
public class PersonalListPresenter extends MvpPresenter<PersonalListView> {
    private static String TAG = "PersonalListPresenter";
    private List<User> userList = new ArrayList<>();
    private List<Role> rolesList = new ArrayList<>();

    public PersonalListPresenter ()
    {
        loadUserListFromServer();
        loadRoles();
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    private void loadRoles() {
        Single<Response<List<Role>>> single = AppData.getApiHelper().getAllRoles();
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(rolesResponse -> {
            if(rolesResponse.isSuccessful())
            {
                this.rolesList = rolesResponse.body();
            }
            else
            {
                getViewState().showAlertDialog(rolesResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }

    public void loadUserListFromServer() {
        Single<Response<List<User>>> single = AppData.getApiHelper().getActualUsers();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userResponse -> {
            if(userResponse.isSuccessful())
            {
                this.userList = userResponse.body();
                getViewState().setDataToAdapter(userList);
            }
            else
            {
                getViewState().showAlertDialog(userResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }

    public void deleteUser(User user)
    {
        Single<Response<ResponseBody>> single = AppData.getApiHelper().deleteUser(user.getId());

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
            if(requestMsg.isSuccessful())
            {
                userList.remove(user);
                getViewState().setDataToAdapter(userList);
                getViewState().showToast(R.string.user_deleted);
            }
            else
            {
                getViewState().showAlertDialog(requestMsg.errorBody().string());
            }

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void editUser(User user, User oldUser)
    {
        Single<Response<ResponseBody>> single = AppData.getApiHelper().editUser(user.getId(), user);

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
            if(requestMsg.isSuccessful())
            {
                updateUser(oldUser, user);
                getViewState().setDataToAdapter(userList);
                getViewState().showToast(R.string.user_edited);
            }
            else
            {
                getViewState().showAlertDialog(requestMsg.errorBody().string());
            }

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void addUser(User user) {
        Single<Response<User>> single = AppData.getApiHelper().addUser(user);

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
            if(requestMsg.isSuccessful())
            {
                userList.add(requestMsg.body());
                getViewState().setDataToAdapter(userList);
//                getViewState().updateRecyclerView();
                getViewState().showToast(R.string.user_added);
            }
            else
            {
                getViewState().showAlertDialog(requestMsg.errorBody().string());
            }

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void updateUser(User old, User newContractor)
    {
        if(userList != null)
        {
            userList.set(userList.indexOf(old), newContractor);
        }
    }
}
