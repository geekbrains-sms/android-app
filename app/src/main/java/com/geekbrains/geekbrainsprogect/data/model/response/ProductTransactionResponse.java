package com.geekbrains.geekbrainsprogect.data.model.response;

import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductTransactionResponse {
    private List<ProductTransaction> productTransactions;

    public List<ProductTransaction> getProductTransactions() {
        if(productTransactions == null)
        {
            productTransactions = new ArrayList<>();
        }
        return productTransactions;
    }
}
