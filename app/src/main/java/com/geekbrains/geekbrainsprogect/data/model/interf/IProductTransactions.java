package com.geekbrains.geekbrainsprogect.data.model.interf;

import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.Date;

public interface IProductTransactions {
    long getId();
    Contractor getContractor();
    IUser getUser();
    String getDate();
    double getQuantity();
    String getComment();
    long getProductId();
}
