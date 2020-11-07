package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

import java.util.Date;

public class ProductTransactionModel implements IProductTransactions {
    private long id;
    private Contractor contractor;
    private UserModel user;
    private String date;
    private double quantity;
    private String comment;
    private long productId;
    private ProductModel productModel;


    public ProductTransactionModel(long id, Contractor contractor, UserModel user, String date, double quantity, String comment, long productId, ProductModel productModel) {
        this.id = id;
        this.contractor = contractor;
        this.user = user;
        this.date = date;
        this.quantity = quantity;
        this.comment = comment;
        this.productId = productId;
        this.productModel = productModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public IProduct getProduct() {
        return productModel;
    }

    @Override
    public long getProductId() {
        return productId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
