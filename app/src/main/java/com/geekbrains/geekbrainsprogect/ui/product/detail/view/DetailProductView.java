package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseView;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.EditProductData;

import java.util.List;

public interface DetailProductView extends BaseView {
    void leftArrowVisibility(boolean visibility);
    void rightArrowVisibility(boolean visibility);
    void showDialogSupply(ProductModel product, List<Contractor>contractors);
    void showDialogShipment(ProductModel product, List<Contractor>contractors);
    void showTransactionListDialog(List<ProductTransactionModel> body);
    void setVisibilityChangedButton(boolean flag);
    void updatePage(ProductModel fund);
    void showEditDialog(ProductModel currentProduct, EditProductData editProductData);
}
