package com.geekbrains.geekbrainsprogect.ui.product.model;

import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.User;

import java.util.Date;

public class ProductTransaction {
    long id;
    Product product;
    Contractor contractor;
    User user;
    Date date;
    double quantity;


    public ProductTransaction(Product product, Contractor contractor, double count) {
        this.product = product;
        this.contractor = contractor;
        quantity = count;
    }

    public Product getProduct() {
        return product;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public User getUser() {
        return user;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
