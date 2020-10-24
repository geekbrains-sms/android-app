package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;
import android.os.Bundle;
import android.view.Menu;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter.PersonalListPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.presenter.InjectPresenter;

public class PersonalListActivity extends ListActivity implements PersonalListView{
    @InjectPresenter
    PersonalListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView personalList;
    @BindView(R.id.add_product_float_action)
    FloatingActionButton actionButton;
    PersonalListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        recyclerSetting();
        setListeners();
    }

    private void setListeners() {
//        actionButton.setOnClickListener(v -> showAddPersonalDialog());
//        adapter.setOnItemClickListener(new BaseListAdapter.IOnItemClickListener<User>() {
//            @Override
//            public void onItemClick(User item) {
//                showEditPersonalDialog(item);
//            }
//
//            @Override
//            public void onItemChangeChecked() {
//                invalidateOptionsMenu();
//            }
//        });
    }

    private void showAddPersonalDialog() {
        DialogFragment personalDialog = new PersonalDialog((user, userOld) -> {
            presenter.addUser(user);
        }, presenter.getRolesList());
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
    public void setDataToAdapter(List<User> body) {
//        adapter.setItemList(body);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }


    public void showEditPersonalDialog(User user) {
        DialogFragment personalDialog = new PersonalDialog(user, (userEdit, oldUser) -> {
            presenter.editUser(userEdit, oldUser);
        }, presenter.getRolesList());
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
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
//            if(user != null)
//                presenter.deleteUser(user);
        }
    }

    @Override
    protected void open() {}
    @Override
    protected void filter() {}


}