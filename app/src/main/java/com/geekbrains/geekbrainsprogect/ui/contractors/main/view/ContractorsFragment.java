package com.geekbrains.geekbrainsprogect.ui.contractors.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.view.ContractorListActivity;
import com.geekbrains.geekbrainsprogect.ui.contractors.main.presenter.ContractorsPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ContractorsFragment extends MvpAppCompatFragment implements ContractorsView {
    @InjectPresenter
    ContractorsPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contractor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @OnClick({R.id.contractors_list_button})
        void onClick()
        {
            Intent intent = new Intent(getActivity(), ContractorListActivity.class);
            startActivity(intent);
        }
}