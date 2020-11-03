package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;
import android.os.Bundle;
import android.view.Menu;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter.PersonalListPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class PersonalListActivity extends ListActivity implements PersonalListView{
    @InjectPresenter
    PersonalListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView personalList;
    @BindView(R.id.add_product_float_action)
    FloatingActionButton actionButton;
    PersonalListAdapter adapter;
    @Inject
    UserInteractor userInteractor;
    @ProvidePresenter
    PersonalListPresenter providePersonalListPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new PersonalListPresenter(userInteractor);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        recyclerSetting();
        setListeners();
    }

    private void setListeners() {
        actionButton.setOnClickListener(v -> presenter.createNewUserDialog());
        adapter.setOnItemClickListener(new BaseListAdapter.IOnItemClickListener<UserModel>() {
            @Override
            public void onItemClick(UserModel item) {
                presenter.createPersonalDialog(item);
            }

            @Override
            public void onItemChangeChecked() {
                invalidateOptionsMenu();
            }
        });
    }

    public void showAddPersonalDialog(List<Role>allRoles) {
        DialogFragment personalDialog = new PersonalDialog((user) -> {
            presenter.addUser(user);
        }, allRoles);
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
    }

    private void recyclerSetting() {
        adapter = new PersonalListAdapter(getApplicationContext());
        personalList.setAdapter(adapter);
        personalList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        personalList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
        setAdapter(adapter);
    }

    @Override
    public void setDataToAdapter(List<UserModel> body) {
        adapter.setItemList(body);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }


    public void showEditPersonalDialog(UserModel user, List<Role>allRoles) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedList() != null && adapter.getSelectedList().size() > 0) {
            menu.findItem(R.id.bar_search).setVisible(false);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(true);
            menu.findItem(R.id.filter).setVisible(false);
        }
        else
        {
            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void delete() {
        for(UserModel user : adapter.getSelectedList())
        {
            if(user != null)
                presenter.deleteUser(user);
        }
    }

    @Override
    protected void open() {}
    @Override
    protected void filter() {}


    @Override
    public void showEditDialog(UserModel item, List<Role>allRoles) {
        DialogFragment personalDialog = new PersonalDialog(item, (userEdit) -> {
            presenter.editUser(userEdit);
        }, allRoles);
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
    }
}