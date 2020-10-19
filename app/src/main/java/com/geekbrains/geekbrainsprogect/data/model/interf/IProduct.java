package com.geekbrains.geekbrainsprogect.data.model.interf;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;

import java.util.List;

public interface IProduct {
    long getId();
    String getTitle();
    String getDescription();
    String getImagePath();
    double getQuantity();
    List<Category>getCategoryList();
    Unit getUnit();
    List<Contractor>getContractors();
    List<IProductTransactions>getProductTransactions();

}
