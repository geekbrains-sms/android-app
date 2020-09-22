package com.geekbrains.geekbrainsprogect.ui.product.model;

import com.geekbrains.geekbrainsprogect.ui.product_list.model.Category;

public class Product {
    int id;
    String title;
    String description;
    int quantity;
    Category category;

    public Product(int id, String title, String description, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
