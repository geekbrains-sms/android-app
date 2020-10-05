package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
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

    public PersonalListPresenter ()
    {
        loadUserListFromServer();
    }

    public void loadUserListFromServer() {
        Single<Response<List<User>>> single = AppData.getApiHelper().getAllUsers();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userResponse -> {
            if(userResponse.isSuccessful())
            {
                this.userList = userResponse.body();
                getViewState().updateRecyclerView();
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
        Single<Response<ResponseBody>> single = AppData.getApiHelper().deleteUser(user.getId(), user);

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
            if(requestMsg.isSuccessful())
            {
                getViewState().updateRecyclerView();
                getViewState().showToast(R.string.user_deleted);
            }
            else
            {
                getViewState().showAlertDialog(requestMsg.errorBody().string());
            }

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

    public void editUser(User user)
    {
        Single<Response<ResponseBody>> single = AppData.getApiHelper().editUser(user.getId(), user);

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(requestMsg -> {
            if(requestMsg.isSuccessful())
            {
                getViewState().updateRecyclerView();
                getViewState().showToast(R.string.user_deleted);
            }
            else
            {
                getViewState().showAlertDialog(requestMsg.errorBody().string());
            }

        }, throwable -> getViewState().showAlertDialog(throwable.getMessage()));
    }

}
