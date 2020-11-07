package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

public class ProductTransactionData implements IProductTransactions {
    @Embedded
    private ProductTransaction productTransaction;
    @Relation(
            parentColumn = "contractor_id",
            entityColumn = "contractorId"
    )
    private Contractor contractor;
    @Relation(
            parentColumn = "user_id",
            entityColumn = "userId"
    )
    private final User user;


    public ProductTransactionData(ProductTransaction productTransaction, Contractor contractor, User user) {
        this.productTransaction = productTransaction;
        this.contractor = contractor;
        this.user = user;
    }

    public ProductTransaction getProductTransaction() {
        return productTransaction;
    }

    public void setProductTransaction(ProductTransaction productTransaction) {
        this.productTransaction = productTransaction;
    }

    @Override
    public long getId() {
        return productTransaction.getId();
    }

    public Contractor getContractor() {
        return contractor;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public String getDate() {
        return productTransaction.getDate();
    }

    @Override
    public double getQuantity() {
        return productTransaction.getQuantity();
    }

    @Override
    public String getComment() {
        return productTransaction.getComment();
    }

    @Override
    public IProduct getProduct() {
        return null;
    }

    @Override
    public long getProductId() {
        return productTransaction.getProductId();
    }


    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}
