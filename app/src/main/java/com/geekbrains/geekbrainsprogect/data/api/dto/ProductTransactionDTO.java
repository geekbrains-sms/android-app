package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;

import java.util.Date;

public class ProductTransactionDTO implements IProductTransactions {
    private Long id;
    private String transactionDate;
    private ProductDTO product;
    private Contractor contractor;
    private double quantity;
    private String comment;
    private UserDTO user;

    public ProductTransactionDTO(Long id, String transactionDate, ProductDTO product, Contractor contractor, double quantity, String comment, UserDTO user) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.product = product;
        this.contractor = contractor;
        this.quantity = quantity;
        this.comment = comment;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public IProduct getProduct() {
        return (IProduct) product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
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

    public UserDTO getUser() {
        return user;
    }

    @Override
    public String getDate() {
        return transactionDate;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
