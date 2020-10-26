package com.geekbrains.geekbrainsprogect.ui.product.product_list.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListModel {
    private static List<ProductModel> selectedProductList;

    public static List<ProductModel> getSelectedProductList()
    {
        if(selectedProductList == null)
        {
            selectedProductList = new ArrayList<>();
        }
        return selectedProductList;
    }
    public static void setSelectedProductList(List<ProductModel>list)
    {
        selectedProductList = list;
    }
}
