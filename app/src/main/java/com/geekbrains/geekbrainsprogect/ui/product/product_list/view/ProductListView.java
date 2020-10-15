package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import moxy.MvpView;

public interface ProductListView extends MvpView {
    void refreshRecyclerView();
    void showAlertDialog(String message);
    void setDataToAdapter();
    void showToast(int product_create_sucesses);
    void updateDisplay();
}
