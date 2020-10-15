package com.geekbrains.geekbrainsprogect.data.model.entity;

import java.util.List;

public class Product {
    int id;
    String title;
    String description;
    Unit unit;
    String imagePath;
    List<Category>categories;
    private List<Contractor> contractors;
    private List<ProductTransaction>transactions;
    private boolean changed = false;


    public Product(String title, String description, List<Category>categories, Unit unit) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUnitsTitle() {
        return unit.getTitle();
    }

    public Unit getUnit() {
        return unit;
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<Category> getCategoryList() {
        return categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnits(Unit unit) { this.unit = unit;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categories = categoryList;
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
            builder.append(category.title);
        }
        return builder.toString();
    }

    public void setTransactions(List<ProductTransaction> transactions) {
        this.transactions = transactions;
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

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged() {
        return changed;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", units='" + getUnitsTitle() + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", categoryList=" + categories +
                '}';
    }
}
