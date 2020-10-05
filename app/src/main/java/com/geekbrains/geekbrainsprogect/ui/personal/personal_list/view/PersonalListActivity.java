package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.presenter.PersonalListPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class PersonalListActivity extends MvpAppCompatActivity implements PersonalListView{
    @InjectPresenter
    PersonalListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView personalList;
    @BindView(R.id.add_product_float_action)
    FloatingActionButton actionButton;
    @Inject
    PersonalListAdapter adapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        AppData.getAppComponent().inject(this);
        recyclerSetting();
        createToolbar();
        setListeners();
    }

    private void setListeners() {
        actionButton.setOnClickListener(v -> showAddPersonalDialog());
        adapter.setOnCheckedClickListener(this::invalidateOptionsMenu);
    }

    private void showAddPersonalDialog() {
        DialogFragment personalDialog = new PersonalDialog(user -> {
            presenter.addUser(user);
        }, presenter.getRolesList());
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
    }

    private void recyclerSetting() {
        adapter.setOnItemClickListener(this::showEditPersonalDialog);
        personalList.setAdapter(adapter);
        personalList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        personalList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }
    @Override
    public void showToast(int text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setDataToAdapter(List<User> body) {
        adapter.setAllUsers(body);
    }
    @Override
    public void showAlertDialog(String string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(string);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }
    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }
    public void showEditPersonalDialog(User user) {
        DialogFragment personalDialog = new PersonalDialog(user, userEdit -> {
            presenter.editUser(userEdit);
        }, presenter.getRolesList());
        personalDialog.show(getSupportFragmentManager(), "personalDialog");
    }

    public void createToolbar()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedUser() != null && adapter.getSelectedUser().size() > 0) {
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

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) menu.findItem(R.id.bar_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete:
                for(User user : adapter.getSelectedUser())
                {
                    if(user != null)
                    presenter.deleteUser(user);
                }

        }
        return super.onOptionsItemSelected(item);
    }
}