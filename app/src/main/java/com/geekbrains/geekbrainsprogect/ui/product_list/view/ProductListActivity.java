package com.geekbrains.geekbrainsprogect.ui.product_list.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product_list.presenter.ProductListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ProductListActivity extends MvpAppCompatActivity implements ProductListView {
    @InjectPresenter
    ProductListPresenter presenter;
    @BindView(R.id.product_recycler)
    RecyclerView productList;
    @Inject
    ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        AppData.getAppComponent().inject(this);
        createRecycler();
    }

    void createRecycler()
    {
        adapter.setRecyclerProduct(presenter.getRecyclerProduct());
        productList.setAdapter(adapter);
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
}