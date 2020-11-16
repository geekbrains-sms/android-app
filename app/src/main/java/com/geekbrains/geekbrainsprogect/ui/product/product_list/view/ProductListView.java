package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;


import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.base.ListView;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.UnitsWithCategories;


public interface ProductListView extends ListView<ProductModel> {
    void showAddProductDialog(UnitsWithCategories usersWithCategory);
}
