package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.personal.detail.view.DetailUserActivity;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter.PersonalListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class PersonalListActivity extends MvpAppCompatActivity implements PersonalListView{
    @InjectPresenter
    PersonalListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView personalList;
    @Inject
    PersonalListAdapter adapter;

    private PersonalListAdapter personalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        AppData.getAppComponent().inject(this);
        recyclerSetting();
    }

    private void recyclerSetting() {
        personalListAdapter.setOnItemClickListener(new PersonalListAdapter.IOnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(getApplication(), DetailUserActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateRecyclerView() {
        personalListAdapter.notifyDataSetChanged();
    }


    public void showEditPersonalDialog(User user) {
        DialogFragment personalDialog = new PersonalDialog(user -> {
            presenter.editUser(user);
        });
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
    }
}