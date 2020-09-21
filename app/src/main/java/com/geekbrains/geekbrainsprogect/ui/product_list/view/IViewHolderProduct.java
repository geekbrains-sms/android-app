package com.geekbrains.geekbrainsprogect.ui.product_list.view;

import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

public interface IViewHolderProduct {
    void bind(Product product);
    int getPos();
}
