package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;

import java.util.Date;

public class ProductTransactionModel {
    private long id;
    private ProductModel product;
    private ContractorModel contractor;
    private UserModel user;
    private Date date;
    private double quantity;
    private String comment;

    public ProductTransactionModel(long id, ProductModel product, ContractorModel contractor, UserModel user, Date date, double quantity, String comment) {
        this.id = id;
        this.product = product;
        this.contractor = contractor;
        this.user = user;
        this.date = date;
        this.quantity = quantity;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ContractorModel getContractor() {
        return contractor;
    }

    public void setContractor(ContractorModel contractor) {
        this.contractor = contractor;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
