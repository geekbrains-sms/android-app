package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.category.presenter.CategoryPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class CategoryActivity extends MvpAppCompatActivity implements CategoryView {
    @InjectPresenter
    CategoryPresenter presenter;

    @BindView(R.id.data_recycler)
    RecyclerView productList;
    @Inject
    CategoryListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        AppData.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDataToAdapter(List<Category> body) {
        adapter.setAllCategory(getApplicationContext(), body);
    }
}