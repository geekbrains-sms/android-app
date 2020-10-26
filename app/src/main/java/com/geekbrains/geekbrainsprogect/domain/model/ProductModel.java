package com.geekbrains.geekbrainsprogect.domain.model;


import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.ui.base.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements Item, IProduct {
    long id;
    String title;
    String description;
    Unit unit;
    String imagePath;
    List<Category> categories;
    double quantity;
    private List<Contractor> contractors;
    private List<ProductTransactionModel>transactions;
    private boolean changed = false;

    public ProductModel(long id, String title, String description, Unit unit, String imagePath,
                        List<Category> categories, List<Contractor> contractors,
                        List<ProductTransactionModel> transactions, double quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.imagePath = imagePath;
        this.categories = categories;
        this.contractors = contractors;
        this.transactions = transactions;
        this.quantity = quantity;
    }

    public long getId() {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public double getQuantity() {
        return quantity;
    }

    @Override
    public List<Category> getCategoryList() {
        return categories;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    @Override
    public List<IProductTransactions> getProductTransactions() {
        return new ArrayList<>(transactions);
    }

    public void setContractors(List<Contractor> contractors) {
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
        for(Contractor contractor : contractors)
        {
            if(builder.length() != 0)
            {
                builder.append(";");
            }
            builder.append(contractor.getTitle());
        }
        return builder.toString();
    }

    public String getStringQuantity()
    {
        return new DecimalFormat("#.##").format(quantity);
    }

    public String getCategoriesString()
    {
        StringBuilder builder = new StringBuilder();
        for(Category category : categories)
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
