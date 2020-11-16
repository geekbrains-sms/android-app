package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryActivity;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.ProductListModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter.ProductListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ProductListActivity extends ListActivity implements ProductListView {
    private static final String TAG = "ProductListActivity";
    @InjectPresenter
    ProductListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView productList;
    ProductListAdapter adapter;
    private SearchView searchView;
    @Inject
    ProductInteractor productInteractor;

    @ProvidePresenter
    ProductListPresenter provideProductListPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new ProductListPresenter(productInteractor);
    }

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
        setAdapter(adapter);
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
        presenter.loadAddProductDialog();
    }

    public void showAddProductDialog(UnitsWithCategories unitsWithCategories) {
        CreateEditProductDialog createEditProductDialog = new CreateEditProductDialog(product -> presenter.addProduct(product), unitsWithCategories);
        createEditProductDialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void setDataToAdapter(List<ProductModel>productModels) {
        Category category =  (Category) getIntent().getSerializableExtra(CategoryActivity.CATEGORY);
        adapter.setProductList(getApplicationContext(), new ProductListFilter(category, productModels));
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void delete() {
        showAlertDeleteDialog();
    }

    @Override
    protected void open() {
        starDetailActivity();
    }

    @Override
    protected void filter() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedProductId() != null && adapter.getSelectedProductId().size() > 0) {
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

        return super.onCreateOptionsMenu(menu);
    }





    private void starDetailActivity() {
        ProductListModel.setSelectedProductList(adapter.getSelectedProductId());
        Intent intent = new Intent(this, DetailProductActivity.class);
        startActivity(intent);
    }

    @Override
    protected void deleteElement() {
        presenter.deleteProduct(adapter.getSelectedProductId());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}