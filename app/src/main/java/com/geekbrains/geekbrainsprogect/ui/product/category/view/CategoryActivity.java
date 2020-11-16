package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.CategoryInteractor;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.category.presenter.CategoryPresenter;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class CategoryActivity extends ListActivity implements CategoryView {
    public static final String CATEGORY = "Category";
    private static final String TAG = "CategoryActivity";
    @InjectPresenter
    CategoryPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView categoryList;
    CategoryListAdapter adapter;
    @Inject
    CategoryInteractor categoryInteractor;
    @ProvidePresenter
    CategoryPresenter provideCategoryPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new CategoryPresenter(categoryInteractor);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        createRecycler();
    }

    private void createRecycler() {
        adapter = new CategoryListAdapter(getApplicationContext());
        adapter.setOnItemClickListener(new BaseListAdapter.IOnItemClickListener<Category>() {
            @Override
            public void onItemClick(Category item) {
                Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
                intent.putExtra(CATEGORY, item);
                startActivity(intent);
            }

            @Override
            public void onItemChangeChecked() {
                invalidateOptionsMenu();
            }
        });
        categoryList.setAdapter(adapter);
        setAdapter(adapter);
        categoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        categoryList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }

    @Override
    public void setDataToAdapter(List<Category> body) {
        adapter.setItemList(body);
    }


    @Override
    public void updateRecyclerView() {
//        setDataToAdapter(AppData.getCategoryList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

        menu.findItem(R.id.open).setVisible(false);
        menu.findItem(R.id.filter).setVisible(false);

        if(adapter.getSelectedList().size() > 0)
        {
            menu.findItem(R.id.bar_search).setVisible(false);
            menu.findItem(R.id.delete).setVisible(true);
        }
        else
        {
            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.delete).setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
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
    protected void deleteElement() {
        for(Category category : adapter.getSelectedList())
        {
            presenter.deleteCategory(category);
        }
    }

    @Override
    protected void delete() {
        showAlertDeleteDialog();}

    @Override
    protected void open() {}

    @Override
    protected void filter() {}
}