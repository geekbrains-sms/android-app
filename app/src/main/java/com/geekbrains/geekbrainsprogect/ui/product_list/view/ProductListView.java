package com.geekbrains.geekbrainsprogect.ui.product_list.view;

import moxy.MvpView;

public interface ProductListView extends MvpView {
    void refreshRecyclerView();
    void showAlertDialog(String message);
}
