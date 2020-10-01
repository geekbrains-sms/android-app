package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

import moxy.MvpView;

public interface DetailProductView extends MvpView {
    void leftArrowVisibility(boolean visibility);
    void rightArrowVisibility(boolean visibility);
    void createDialogSupply(Product product);
    void createDialogShipment(Product product);
    void setDataToContractorsTextView(String contractorsString);
}
