package com.geekbrains.geekbrainsprogect.domain.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;

import java.text.DecimalFormat;

public class FundModel {
    private long id;
    private Product product;
    private double balance;

    public FundModel(long id, Product product, double balance) {
        this.id = id;
        this.product = product;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStringBalance() {
        return new DecimalFormat("#.##").format(balance);
    }
}
