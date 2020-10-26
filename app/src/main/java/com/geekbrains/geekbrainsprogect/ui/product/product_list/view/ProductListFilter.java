package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListFilter {
    private Category selectCategory;
    private boolean showNullFund = true;
    private List<ProductModel> productModels = new ArrayList<>();

    public ProductListFilter(Category selectCategory, List<ProductModel> productModels)
    {
        this.selectCategory = selectCategory;
        this.productModels = productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }


    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public Category getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(Category selectCategory) {
        this.selectCategory = selectCategory;
    }

    public boolean isShowNullFund() {
        return showNullFund;
    }

    public void setShowNullFund(boolean showNullFund) {
        this.showNullFund = showNullFund;
    }

    public List<ProductModel>filtered()
    {
        List<ProductModel>filtered = filteredForCategory();
        if(!showNullFund)
        {
            filtered = filteredFoNull(filtered);
        }
        return filtered;
    }



    private List<ProductModel> filteredForCategory() {
        List<ProductModel>categoryFilter = new ArrayList<>();
        if(selectCategory != null)
        {
            for(ProductModel productModel : productModels)
                    for(Category category : productModel.getCategoryList())
                    {
                        if(category.getTitle().equals(selectCategory.getTitle()))
                        {
                            categoryFilter.add(productModel);
                        }
                    }
        }
        else
        {
            categoryFilter.addAll(productModels);
        }
        return categoryFilter;
    }
    private List<ProductModel> filteredFoNull(List<ProductModel> filtered) {
        List<ProductModel>filterForNull = new ArrayList<>();
        for(ProductModel productModel : filtered)
        {
            if(productModel.getQuantity() > 0)
            {
                filterForNull.add(productModel);
            }
        }
        return filterForNull;
    }
}
