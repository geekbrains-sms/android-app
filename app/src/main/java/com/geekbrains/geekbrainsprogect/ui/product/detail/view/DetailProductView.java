package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import com.geekbrains.geekbrainsprogect.ui.base.BaseView;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;

import java.util.List;

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
