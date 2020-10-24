package com.geekbrains.geekbrainsprogect.data.api.dto;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;


import java.util.List;

public class FundDTO implements IProduct{
    private Long id;
    private ProductDTO product;
    private Double balance;

    public FundDTO(Long id, ProductDTO product, Double balance) {
        this.id = id;
        this.product = product;
        this.balance = balance;
    }

    public long getId() {
        return product.getId();
    }



    @Override
    public String getTitle() {
        return product.getTitle();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    @Override
    public String getImagePath() {
        return product.getImagePath();
    }

    @Override
    public double getQuantity() {
        return balance;
    }

    @Override
    public List<Category> getCategoryList() {
        return product.getCategoryList();
    }

    @Override
    public Unit getUnit() {
        return product.getUnit();
    }

    @Override
    public List<Contractor> getContractors() {
        return null;
    }

    @Override
    public List<IProductTransactions> getProductTransactions() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
