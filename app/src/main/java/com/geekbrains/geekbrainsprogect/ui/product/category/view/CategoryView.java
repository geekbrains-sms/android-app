package com.geekbrains.geekbrainsprogect.ui.product.category.view;

import com.geekbrains.geekbrainsprogect.ui.product.model.Category;

import java.util.List;

import moxy.MvpView;

public interface CategoryView extends MvpView {
    void setDataToAdapter(List<Category> body);
}
