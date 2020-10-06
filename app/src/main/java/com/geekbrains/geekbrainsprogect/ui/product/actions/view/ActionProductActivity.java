package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.actions.model.UserAction;
import com.geekbrains.geekbrainsprogect.ui.product.actions.presenter.ActionProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ActionProductActivity extends MvpAppCompatActivity implements ActionProductView {
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
        adapter = new ActionProductAdapter();
        dataList.setAdapter(adapter);
        dataList.setLayoutManager(new LinearLayoutManager(getApplication()));
        dataList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }


    @Override
    public void setDataToAdapter(List<UserAction> body) {
        adapter.setData(body);
    }

    @Override
    public void setAlertDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(text);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) menu.findItem(R.id.bar_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
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
}