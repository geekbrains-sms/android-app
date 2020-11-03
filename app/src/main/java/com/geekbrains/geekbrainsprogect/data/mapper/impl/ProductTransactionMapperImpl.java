package com.geekbrains.geekbrainsprogect.data.mapper.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.UserDTO;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductTransactionMapperImpl implements ProductTransactionMapper {
    private static final String TAG = "ProductTransactionMap";
    UserMapper userMapper;
    ProductMapper productMapper;
    @Inject
    public ProductTransactionMapperImpl(UserMapper userMapper, ProductMapper productMapper) {
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ProductTransactionModel toModel(IProductTransactions object) {
        Log.d(TAG, "toModel() start: " + object.toString());
        long id = object.getId();
        long productID = object.getProductId();
        Contractor contractor = object.getContractor();
        UserModel userModel = null;
        if(object.getUser() != null)
        {
            userModel = userMapper.toModel(object.getUser());
        }
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        ProductTransactionModel productTransactionModel = new ProductTransactionModel(id,contractor,userModel,date,quantity,comment, productID);
        Log.d(TAG, "toModel() end: " + productTransactionModel.toString());
        return productTransactionModel;
    }

    @Override
    public ProductTransactionDTO toDto(IProductTransactions object) {
        Log.d(TAG, "toDto() start: " + object.toString());
        long id = object.getId();
        Contractor contractor = object.getContractor();
        UserDTO userDTO = null;
        if(object.getUser() != null)
        {
            userDTO = userMapper.toDto(object.getUser());
        }
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        ProductTransactionDTO productTransactionDTO = new ProductTransactionDTO(id,date,contractor,quantity,comment,userDTO);
        Log.d(TAG, "toDto() end: " + productTransactionDTO.toString());
        return productTransactionDTO;
    }

    @Override
    public ProductTransactionData toEntity(IProductTransactions object) {
        Log.d(TAG, "toEntity() start: " + object.toString());
        long id = object.getId();
        long contractorId = object.getContractor().id;
        long productID = object.getProductId();
        Contractor contractor = object.getContractor();
        User user = userMapper.toEntity(object.getUser());
        long userId = object.getUser().getId();
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        ProductTransaction productTransaction = new ProductTransaction(id, contractorId,userId, date, quantity, comment, productID);
        Log.d(TAG, "toEntity() end: " + productTransaction.toString());
        return new ProductTransactionData(productTransaction, contractor, user);
    }

    @Override
    public List<ProductTransactionModel> toModelList(List<? extends IProductTransactions> list) {
        Log.d(TAG, "toModelList() start");
        List<ProductTransactionModel>modelList = new ArrayList<>();
        for(IProductTransactions transactions: list)
        {
            modelList.add(toModel(transactions));
        }
        Log.d(TAG, "toModelList() end");
        return modelList;
    }

    @Override
    public List<ProductTransactionDTO> toDtoList(List<? extends IProductTransactions> list) {
        Log.d(TAG, "toDtoList() start");
        List<ProductTransactionDTO>dtoList = new ArrayList<>();
        for(IProductTransactions transactions: list)
        {
            dtoList.add(toDto(transactions));
        }
        Log.d(TAG, "toModelList() end");
        return dtoList;
    }

    @Override
    public List<ProductTransactionData> toEntityList(List<? extends IProductTransactions> list) {
        Log.d(TAG, "toEntityList() start");
        List<ProductTransactionData>entityList = new ArrayList<>();
        for(IProductTransactions transactions: list)
        {
            entityList.add(toEntity(transactions));
        }
        Log.d(TAG, "toEntityList() end");
        return entityList;
    }


    @Override
    public List<ProductTransaction> toEntityListProductTransaction(List<? extends IProductTransactions> list) {
        List<ProductTransaction>entityList = new ArrayList<>();
        for(IProductTransactions transactions: list)
        {
            entityList.add(toEntity(transactions).productTransaction);
        }
        return entityList;
    }
}
