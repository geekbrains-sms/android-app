package com.geekbrains.geekbrainsprogect.ui.contractors.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.contractors.presenter.ContractorsPresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ContractorsFragment extends MvpAppCompatFragment implements ContractorsView {
    @InjectPresenter
    ContractorsPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contractor, container, false);
    }
}