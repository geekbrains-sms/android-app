package com.geekbrains.geekbrainsprogect.domain.model;


import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.ui.base.Item;

import java.util.List;

public class ProductModel implements Item {
    int id;
    String title;
    String description;
    UnitModel unit;
    String imagePath;
    List<CategoryModel> categories;
    private List<ContractorModel> contractors;
    private List<ProductTransactionModel>transactions;
    private boolean changed = false;

    public ProductModel(int id, String title, String description, UnitModel unit, String imagePath, List<CategoryModel> categories, List<ContractorModel> contractors, List<ProductTransactionModel> transactions, boolean changed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.imagePath = imagePath;
        this.categories = categories;
        this.contractors = contractors;
        this.transactions = transactions;
        this.changed = changed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<ContractorModel> getContractors() {
        return contractors;
    }

    public void setContractors(List<ContractorModel> contractors) {
        this.contractors = contractors;
    }

    public List<ProductTransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<ProductTransactionModel> transactions) {
        this.transactions = transactions;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getContractorsString()
    {
        StringBuilder builder = new StringBuilder();
        for(ContractorModel contractor : contractors)
        {
            if(builder.length() != 0)
            {
                builder.append(";");
            }
            builder.append(contractor.getTitle());
        }
        return builder.toString();
    }

    public String getCategoriesString()
    {
        StringBuilder builder = new StringBuilder();
        for(CategoryModel category : categories)
        {
            if(builder.length() != 0)
            {
                builder.append("/");
            }
            builder.append(category.getTitle());
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", units='" + getUnit() + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", categoryList=" + categories +
                '}';
    }

    @Override
    public String getItemName() {
        return title;
    }
}
