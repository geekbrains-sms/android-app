package com.geekbrains.geekbrainsprogect.data.mapper;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.UserDTO;
import com.geekbrains.geekbrainsprogect.data.dagger.ProductTransactionModule;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ProductTransactionMapperImpl implements ProductTransactionMapper {
    private static final String TAG = "ProductTransactionMap";
    ProductMapper productMapper;
    UserMapper userMapper;
    @Override
    public ProductTransactionModel toModel(IProductTransactions object) {
        Log.d(TAG, "toModel() start: " + object.toString());
        long id = object.getId();
        Contractor contractor = object.getContractor();
        ProductModel product = productMapper.toModel(object.getProduct());
        UserModel userModel = userMapper.toModel(object.getUser());
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        ProductTransactionModel productTransactionModel = new ProductTransactionModel(id,contractor,userModel,date,quantity,comment,product);
        Log.d(TAG, "toModel() end: " + productTransactionModel.toString());
        return productTransactionModel;
    }

    @Override
    public ProductTransactionDTO toDto(IProductTransactions object) {
        Log.d(TAG, "toDto() start: " + object.toString());
        long id = object.getId();
        Contractor contractor = object.getContractor();
        ProductDTO product = productMapper.toDto(object.getProduct());
        UserDTO userDTO = userMapper.toDto(object.getUser());
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();

        ProductTransactionDTO productTransactionDTO = new ProductTransactionDTO(id,date,product,contractor,quantity,comment,userDTO);
        Log.d(TAG, "toDto() end: " + productTransactionDTO.toString());
        return productTransactionDTO;
    }

    @Override
    public ProductTransaction toEntity(IProductTransactions object) {
        Log.d(TAG, "toEntity() start: " + object.toString());
        long id = object.getId();
        long contractorId = object.getContractor().id;
        long productId = object.getId();
        long userId = object.getUser().getId();
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        ProductTransaction productTransaction = new ProductTransaction(id, productId,contractorId,userId, date, quantity, comment);
        Log.d(TAG, "toEntity() end: " + productTransaction.toString());
        return productTransaction;
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
    public List<ProductTransaction> toEntityList(List<? extends IProductTransactions> list) {
        Log.d(TAG, "toEntityList() start");
        List<ProductTransaction>entityList = new ArrayList<>();
        for(IProductTransactions transactions: list)
        {
            entityList.add(toEntity(transactions));
        }
        Log.d(TAG, "toEntityList() end");
        return entityList;
    }
}
