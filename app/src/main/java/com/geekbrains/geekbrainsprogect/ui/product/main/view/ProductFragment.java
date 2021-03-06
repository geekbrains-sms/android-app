package com.geekbrains.geekbrainsprogect.ui.product.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.ui.product.actions.view.ActionProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryActivity;
import com.geekbrains.geekbrainsprogect.ui.product.main.presenter.ProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ProductFragment extends MvpAppCompatFragment implements ProductView {
    @InjectPresenter
    ProductPresenter presenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.product_list_button, R.id.actions_product_button})
    void onClick(View view){
        switch (view.getId())
        {
            case R.id.product_list_button:
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.actions_product_button:
                Intent intent2 = new Intent(getActivity(), ActionProductActivity.class);
                startActivity(intent2);
                break;
        }
    }
}