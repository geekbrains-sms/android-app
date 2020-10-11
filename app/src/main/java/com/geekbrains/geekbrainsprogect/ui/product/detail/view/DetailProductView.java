package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import com.geekbrains.geekbrainsprogect.ui.base.BaseView;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;

import java.util.List;

import moxy.MvpView;
import okhttp3.ResponseBody;

public interface DetailProductView extends BaseView {
    void leftArrowVisibility(boolean visibility);
    void rightArrowVisibility(boolean visibility);
    void createDialogSupply(Product product);
    void createDialogShipment(Product product);
    void setDataToContractorsTextView(String contractorsString);
    void showTransactionListDialog(List<ProductTransaction> body);
    void setVisibilityChangedButton(boolean flag);
    void showEditUnitsDialog(Product product);
    void updatePage(Fund fund);
}
