package com.geekbrains.geekbrainsprogect.ui.product.model;

import java.io.File;
import java.text.DecimalFormat;

public class Fund {
    long id;
    Product product;
    double balance;

    public Fund(Product product)
    {
        this.product = product;
        balance = 0;
    }

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
