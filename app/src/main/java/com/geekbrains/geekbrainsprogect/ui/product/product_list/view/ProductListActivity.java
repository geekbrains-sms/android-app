package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter.ProductListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ProductListActivity extends MvpAppCompatActivity implements ProductListView {
    @InjectPresenter
    ProductListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView productList;
    @Inject
    ProductListAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        AppData.getAppComponent().inject(this);
        createRecycler();
        createToolbar();
    }

    void createRecycler()
    {
        productList.setAdapter(adapter);
        productList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
        productList.setLayoutManager(new LinearLayoutManager(getApplication()));
    }


    @Override
    public void refreshRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }

    @Override
    public void setDataToAdapter(List<Fund> products) {
        adapter.setProductList(getApplicationContext() ,products);
    }

    public void createToolbar()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

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
            case R.id.bar_search:
                break;
            case R.id.open:
                AppData.setSelectedProducts(adapter.getSelectedProduct());
                Intent intent = new Intent(this, DetailProductActivity.class);
                startActivity(intent);
                break;
            case R.id.delete:
                break;
            case R.id.filter:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null)
        {
            adapter.notifyDataSetChanged();
        }
    }
}