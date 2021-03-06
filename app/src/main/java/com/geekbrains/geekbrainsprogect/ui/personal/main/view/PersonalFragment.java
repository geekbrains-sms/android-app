package com.geekbrains.geekbrainsprogect.ui.personal.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.personal.main.presenter.PersonalPresenter;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class PersonalFragment extends MvpAppCompatFragment implements PersonalView {
    @InjectPresenter
    PersonalPresenter presenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.personal_menegment_button})
    void onClick()
    {
        Intent intent = new Intent(getActivity(), PersonalListActivity.class);
        startActivity(intent);
    }
}