package com.geekbrains.geekbrainsprogect.ui.personal_list.view;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.personal_list.presenter.PersonalListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class PersonalListActivity extends MvpAppCompatActivity implements PersonalListView {
    @InjectPresenter
    PersonalListPresenter presenter;
    @BindView(R.id.personal_list)
    RecyclerView recyclerPersonal;
    private PersonalListAdapter personalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_list);
        ButterKnife.bind(this);
        recyclerSetting();
    }

    private void recyclerSetting()
    {
        personalListAdapter = new PersonalListAdapter(presenter.getRecyclerPersonal());
        recyclerPersonal.setAdapter(personalListAdapter);
        recyclerPersonal.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void updateRecyclerView() {
        personalListAdapter.notifyDataSetChanged();
    }
}