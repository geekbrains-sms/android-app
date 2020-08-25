package com.geekbrains.geekbrainsprogect.ui.product.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.presenter.ProductPresenter;

import moxy.InjectViewState;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ProductFragment extends MvpAppCompatFragment implements ProductView {
    @InjectPresenter
    ProductPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods, container, false);
    }
}