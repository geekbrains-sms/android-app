package com.geekbrains.geekbrainsprogect.ui.product.model;

import java.text.DecimalFormat;

public class Fund {
    long id;
    Product product;
    double balance;

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public double getBalance() {
        return balance;
    }

    public String getStringBalance()
    {
        return new DecimalFormat("#.##").format(balance);
    }
}
