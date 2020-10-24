package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

import moxy.MvpView;

public interface ProductListView extends MvpView {
    void refreshRecyclerView();
    void showAlertDialog(String message);
    void setDataToAdapter(List<ProductModel> productModelList);
    void showToast(int product_create_sucesses);
    void updateDisplay();
}
