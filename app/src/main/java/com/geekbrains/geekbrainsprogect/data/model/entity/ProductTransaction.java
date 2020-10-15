package com.geekbrains.geekbrainsprogect.data.model.entity;

import java.text.DecimalFormat;
import java.util.Date;

public class ProductTransaction {
    long id;
    Product product;
    Contractor contractor;
    User user;
    Date date;
    double quantity;
    private String comment;


    public ProductTransaction(Product product, Contractor contractor, double count, String comment) {
        this.product = product;
        this.contractor = contractor;
        quantity = count;
        this.comment = comment;
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

    public double getQuantity() {
        return quantity;
    }

    public String getStringQuantity()
    {
        return new DecimalFormat("#.##").format(quantity);
    }

    public Date getDate() {
         return date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
