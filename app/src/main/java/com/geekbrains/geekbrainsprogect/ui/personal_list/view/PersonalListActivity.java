package com.geekbrains.geekbrainsprogect.ui.personal_list.view;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.PersonalDialog;
import com.geekbrains.geekbrainsprogect.ui.personal_list.presenter.PersonalListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class PersonalListActivity extends MvpAppCompatActivity implements PersonalListView{
    @InjectPresenter
    PersonalListPresenter presenter;
//    @BindView(R.id.personal_list)
//    ContextMenuRecyclerView recyclerPersonal;
    private PersonalListAdapter personalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
//        ButterKnife.bind(this);
//        recyclerSetting();
    }

    @Override
    public void updateRecyclerView() {

    }

    @Override
    public void showToast(String msg) {

    }

//    private void recyclerSetting()
//    {
//        personalListAdapter = new PersonalListAdapter(presenter.getRecyclerPersonal());
//        personalListAdapter.setOnItemLongClickListener((position, view) -> {
//        });
//        recyclerPersonal.setAdapter(personalListAdapter);
//        recyclerPersonal.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        registerForContextMenu(recyclerPersonal);
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        ContextMenuRecyclerView.RecyclerViewContextMenuInfo info = (ContextMenuRecyclerView.RecyclerViewContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId())
//        {
//            case R.id.edit:
//                showEditPersonalDialog(presenter.getUserFromPosition(info.position));
//                break;
//            case R.id.delete:
//                presenter.deleteUser(info.position);
//        }
//        return super.onContextItemSelected(item);
//    }
//
//    @Override
//    public void showToast(String msg) {
//        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void updateRecyclerView() {
//        personalListAdapter.notifyDataSetChanged();
//    }
//
//
//    public void showEditPersonalDialog(User user) {
//        DialogFragment personalDialog = new PersonalDialog((login, password, fullname) -> {
//            presenter.editUser(login, password, fullname);
//        });
//        personalDialog.show(getSupportFragmentManager(), "personalDialog");
//    }
}