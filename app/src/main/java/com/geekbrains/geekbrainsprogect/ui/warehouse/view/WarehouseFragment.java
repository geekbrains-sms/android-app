package com.geekbrains.geekbrainsprogect.ui.warehouse.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.warehouse.presenter.WarehousePresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class WarehouseFragment extends MvpAppCompatFragment implements WarehouseView {
    @InjectPresenter
    WarehousePresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_warehouse, container, false);
    }
}