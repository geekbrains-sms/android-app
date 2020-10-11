package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.actions.model.UserAction;
import com.geekbrains.geekbrainsprogect.ui.product.actions.presenter.ActionProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ActionProductActivity extends ListActivity implements ActionProductView {
    @InjectPresenter
    ActionProductPresenter presenter;
    ActionProductAdapter adapter;
    @BindView(R.id.add_product_float_action)
    FloatingActionButton addButton;
    @BindView(R.id.data_recycler)
    RecyclerView dataList;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        ButterKnife.bind(this);
        addButton.setVisibility(View.GONE);
        createRecycler();
    }

    private void createRecycler() {
        adapter = new ActionProductAdapter(getApplicationContext());
        dataList.setAdapter(adapter);
        dataList.setLayoutManager(new LinearLayoutManager(getApplication()));
        dataList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }


    @Override
    public void setDataToAdapter(List<UserAction> body) {
        adapter.setItemList(body);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void delete() {}

    @Override
    protected void open() {}

    @Override
    protected void filter() {}
}