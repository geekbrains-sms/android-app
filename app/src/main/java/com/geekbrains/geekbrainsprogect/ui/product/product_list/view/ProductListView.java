package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import java.util.List;

import moxy.MvpView;

public interface ProductListView extends MvpView {
    void refreshRecyclerView();
    void showAlertDialog(String message);
    void setDataToAdapter(List<Fund>products);
    void showToast(int product_create_sucesses);
    void updateDisplay();
}
