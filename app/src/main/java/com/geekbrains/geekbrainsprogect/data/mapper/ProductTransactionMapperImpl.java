package com.geekbrains.geekbrainsprogect.data.mapper;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;

import java.util.List;

public class ProductTransactionMapperImpl implements ProductTransactionMapper {
    ProductMapper productMapper;
    UserMapper userMapper;
    @Override
    public ProductTransactionModel toModel(IProductTransactions object) {
        long id = object.getId();
        Contractor contractor = object.getContractor();
        ProductModel product = productMapper.toModel(object.getProduct());
        UserModel userModel = userMapper.toModel(object.getUser());
        double quantity = object.getQuantity();
        String comment = object.getComment();
        String date = object.getDate();
        return new ProductTransactionModel(id,contractor,userModel,date,quantity,comment,product);
    }

    @Override
    public ProductTransactionDTO toDto(IProductTransactions object) {
        return null;
    }

    @Override
    public ProductTransaction toEntity(IProductTransactions object) {
        return null;
    }

    @Override
    public List<ProductTransactionModel> toModelList(List<? extends IProductTransactions> list) {
        return null;
    }

    @Override
    public List<ProductTransactionDTO> toDtoList(List<? extends IProductTransactions> list) {
        return null;
    }

    @Override
    public List<ProductTransaction> toEntityList(List<? extends IProductTransactions> list) {
        return null;
    }
}
