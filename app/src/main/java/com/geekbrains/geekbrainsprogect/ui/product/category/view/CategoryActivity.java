package com.geekbrains.geekbrainsprogect.ui.product.category.view;

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

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.category.presenter.CategoryPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class CategoryActivity extends MvpAppCompatActivity implements CategoryView {
    public static final String CATEGORY = "Category";
    private static final String TAG = "CategoryActivity";
    @InjectPresenter
    CategoryPresenter presenter;

    @BindView(R.id.data_recycler)
    RecyclerView categoryList;
    CategoryListAdapter adapter;
    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        
        createRecycler();
    }

    private void createRecycler() {
        adapter = new CategoryListAdapter();
        adapter.setSelectItemListener(new CategoryListAdapter.ISelectItemListener() {
            @Override
            public void onItemClick(Category category) {
                Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
                intent.putExtra(CATEGORY, category);
                startActivity(intent);
            }
            @Override
            public void onLongItemClick() {
                invalidateOptionsMenu();
            }
        });



        categoryList.setAdapter(adapter);
        categoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }

    @Override
    public void setDataToAdapter(List<Category> body) {
        adapter.setAllCategory(getApplicationContext(), body);
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
    public void showToast(int text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateRecyclerView() {
        setDataToAdapter(AppData.getCategoryList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

        menu.findItem(R.id.open).setVisible(false);
        menu.findItem(R.id.filter).setVisible(false);

        if(adapter.getSelectedCategories().size() > 0)
        {
            menu.findItem(R.id.bar_search).setVisible(false);
            menu.findItem(R.id.delete).setVisible(true);
        }
        else
        {
            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.delete).setVisible(false);
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

    @OnClick({R.id.add_product_float_action})
    void onClick(View view)
    {
        showAddCategoryDialog();
    }

    private void showAddCategoryDialog() {
        CreateCategoryDialog dialog = new CreateCategoryDialog(category -> presenter.saveCategory(category));
        dialog.show(getSupportFragmentManager(),  TAG);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete:
                showAlertDeleteDialog();
                break;
//            case R.id.filter:
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.alert_delete_message)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    for(Category category : adapter.getSelectedCategories())
                    {
                        presenter.deleteCategory(category);
                    }
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {});
        builder.create().show();
    }


}