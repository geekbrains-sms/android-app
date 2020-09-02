package com.geekbrains.geekbrainsprogect.ui.personal_list.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.personal_list.view.IRecyclerPersonal;
import com.geekbrains.geekbrainsprogect.ui.personal_list.view.IViewHolder;
import com.geekbrains.geekbrainsprogect.ui.personal_list.view.PersonalListView;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
@InjectViewState
public class PersonalListPresenter extends MvpPresenter<PersonalListView> {
    private static String TAG = "PersonalListPresenter";
    private List<User> userList = new ArrayList<>();
    RecyclerPersonal recyclerPersonal;

    public PersonalListPresenter ()
    {
        recyclerPersonal = new RecyclerPersonal();
    }


    public RecyclerPersonal getRecyclerPersonal() {
        return recyclerPersonal;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadUserListFromServer();
    }

    public void loadUserListFromServer() {
        Single<List<User>> single = AppData.getApiHelper().requestAllUsers();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userList -> {
            this.userList = userList;
            Log.e(TAG, "loadFromServer: " + userList.size());
            getViewState().updateRecyclerView();
        }, throwable -> {
            Log.e(TAG, "onError2 " + throwable);
        });
    }




    private class RecyclerPersonal implements IRecyclerPersonal
    {

        @Override
        public void bindView(IViewHolder iViewHolder) {
            iViewHolder.bind(userList.get(iViewHolder.getPos()));
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }
}
