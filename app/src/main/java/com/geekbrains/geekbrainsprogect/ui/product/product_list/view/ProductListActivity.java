package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryActivity;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter.ProductListPresenter;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ProductListActivity extends MvpAppCompatActivity implements ProductListView {
    private static final String TAG = "ProductListActivity";
    @InjectPresenter
    ProductListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView productList;
    ProductListAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        createRecycler();
        createToolbar();
    }

    void createRecycler()
    {
        adapter = new ProductListAdapter();
        productList.setAdapter(adapter);
        adapter.setIOnClickListener(new ProductListAdapter.IOnClickListener() {
            @Override
            public void onClick() {
                invalidateOptionsMenu();
            }

            @Override
            public void onSingleClick() {
               starDetailActivity();
            }
        });
        productList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
        productList.setLayoutManager(new LinearLayoutManager(getApplication()));
    }

    @OnClick({R.id.add_product_float_action})
    public void onClick(View view)
    {
        showAddProductDialog();
    }

    private void showAddProductDialog() {
        CreateProductDialog createProductDialog = new CreateProductDialog(product -> presenter.addProductToServer(product));
        createProductDialog.show(getSupportFragmentManager(), TAG);
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
    public void setDataToAdapter() {
        Category category =  (Category) getIntent().getSerializableExtra(CategoryActivity.CATEGORY);
        adapter.setProductList(getApplicationContext(), new ProductListFilter(category));
    }

    @Override
    public void showToast(int text) {
        Toast.makeText(getApplicationContext(), text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDisplay() {
        adapter.notifyDataSetChanged();
    }

    public void createToolbar()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedProduct() != null && adapter.getSelectedProduct().size() > 0) {
            menu.findItem(R.id.bar_search).setVisible(false);
            menu.findItem(R.id.open).setVisible(true);
            menu.findItem(R.id.delete).setVisible(true);
            menu.findItem(R.id.filter).setVisible(false);
        }
        else
        {
            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(true);
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
            case R.id.open:
                starDetailActivity();
                break;
            case R.id.delete:
                showAlertDeleteDialog();
                break;
            case R.id.filter:
                break;
            case android.R.id.home:
            {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDeleteDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.alert_delete_message)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> presenter.deleteProduct(adapter.getSelectedProduct()))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {});
        builder.create().show();
    }

    private void starDetailActivity() {
        AppData.setSelectedProducts(adapter.getSelectedProduct());
        Intent intent = new Intent(this, DetailProductActivity.class);
        startActivity(intent);
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
    protected void onResume() {
        super.onResume();
        if(adapter != null)
        {
            setDataToAdapter();
        }
    }
}