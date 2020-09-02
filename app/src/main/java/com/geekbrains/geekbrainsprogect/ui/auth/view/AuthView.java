package com.geekbrains.geekbrainsprogect.ui.auth.view;

import moxy.MvpView;

public interface AuthView extends MvpView {
    void showToast(int message);
    void showAlertDialog(String message);
    void startMainActivity();
}
