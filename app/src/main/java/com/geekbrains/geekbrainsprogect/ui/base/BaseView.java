package com.geekbrains.geekbrainsprogect.ui.base;

import moxy.MvpView;

public interface BaseView extends MvpView {
    void showToast(int text);
    void showAlertDialog(String text);
}
