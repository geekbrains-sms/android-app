package com.geekbrains.geekbrainsprogect.data.api.model;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.google.gson.annotations.Expose;

public class ProductTransactionDTO implements IProductTransactions {

    @Expose
    private Long id;
    @Expose(serialize = false)
    private String transactionDate;
    @Expose
    private Contractor contractor;
    @Expose
    private double quantity;
    @Expose
    private ProductDTO product;
    @Expose
    private String comment;
    @Expose
    private UserDTO user;

    public ProductTransactionDTO(Long id, String transactionDate, Contractor contractor, double quantity, String comment, UserDTO user) {
        this.id = id;
        this.transactionDate = transactionDate;
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

    @Override
    public long getProductId() {
        return product.getId();
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
