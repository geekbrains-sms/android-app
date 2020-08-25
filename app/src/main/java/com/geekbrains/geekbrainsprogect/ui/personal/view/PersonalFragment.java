package com.geekbrains.geekbrainsprogect.ui.personal.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.personal.presenter.PersonalPresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class PersonalFragment extends MvpAppCompatFragment implements PersonalView {
    @InjectPresenter
    PersonalPresenter presenter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }
}