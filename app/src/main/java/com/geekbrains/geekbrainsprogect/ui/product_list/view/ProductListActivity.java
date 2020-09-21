package com.geekbrains.geekbrainsprogect.ui.product_list.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product_list.presenter.ProductListPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ProductListActivity extends MvpAppCompatActivity {
    @InjectPresenter
    ProductListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
    }
}