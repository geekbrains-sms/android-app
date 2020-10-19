package com.geekbrains.geekbrainsprogect.ui.product.product_list.view;

import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFilter {
    private Category selectCategory;
    private boolean showNullFund = true;
    private List<Fund>fundList = new ArrayList<>();

    public ProductListFilter(Category selectCategory)
    {
        this.selectCategory = selectCategory;
        List<Fund>funds = AppData.getProductList();
        if(funds != null)
        {
            fundList.addAll(funds);
        }
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
    }


    public List<Fund> getFundList() {
        return fundList;
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

    public List<Fund>filtered()
    {
        List<Fund>filtered = filteredForCategory();
        if(!showNullFund)
        {
            filtered = filteredFoNull(filtered);
        }
        return filtered;
    }



    private List<Fund> filteredForCategory() {
        List<Fund>categoryFilter = new ArrayList<>();
        if(selectCategory != null)
        {
            for(Fund fund : fundList)
            {
                 Product product = fund.getProduct();
                    for(Category category : product.getCategoryList())
                    {
                        if(category.getTitle().equals(selectCategory.getTitle()))
                        {
                            categoryFilter.add(fund);
                        }
                    }
            }
        }
        else
        {
            categoryFilter.addAll(fundList);
        }
        return categoryFilter;
    }
    private List<Fund> filteredFoNull(List<Fund> filtered) {
        List<Fund>filterForNull = new ArrayList<>();
        for(Fund fund : filtered)
        {
            if(fund.getBalance() > 0)
            {
                filterForNull.add(fund);
            }
        }
        return filterForNull;
    }
}
