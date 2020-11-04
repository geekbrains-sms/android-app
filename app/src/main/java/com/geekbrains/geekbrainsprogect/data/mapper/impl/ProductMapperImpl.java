package com.geekbrains.geekbrainsprogect.data.mapper.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.model.Image;
import com.geekbrains.geekbrainsprogect.data.api.model.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMapperImpl implements ProductMapper {
    private static final String TAG = "ProductMapper";


    @Override
    public ProductModel toModel(IProduct object) {
        Log.d(TAG, "toModel() start: " + object.toString());
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        Unit unit = object.getUnit();
        double quantity = object.getQuantity();
        List<Contractor>contractors = object.getContractors();
        List<Category>categories = object.getCategoryList();

        ProductModel productModel = new ProductModel(id,title,description,unit,imageUrl,categories,contractors,quantity);
        Log.d(TAG, "toModel() end: " + productModel.toString());
        return productModel;
    }

    @Override
    public ProductDTO toDto(IProduct object) {
        Log.d(TAG, "toDto() start: " + object.toString());
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        List<Category>categories = object.getCategoryList();
        Unit unit = object.getUnit();
        Image image = new Image(0L,null, imageUrl);

        ProductDTO productDTO = new ProductDTO(id,title,categories,unit, image,description);
        Log.d(TAG, "toDto() end: " + productDTO.toString());
        return productDTO;
    }

    @Override
    public ProductWithCategory toEntity(IProduct object) {
        Log.d(TAG, "toEntity() start: " + object.toString());
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        long unitId = object.getUnit().getId();
        double quantity = object.getQuantity();
        List<Contractor>contractors = object.getContractors();
        List<Category>categories = object.getCategoryList();
        Unit unit = object.getUnit();
        Product product = new Product(id,title,description,unitId,quantity, imageUrl);
        ProductWithCategory productWithCategory = new ProductWithCategory(product,categories,contractors, unit);
        Log.d(TAG, "toEntity() end: " + productWithCategory.toString());
        return productWithCategory;
    }

    @Override
    public List<ProductModel> toModelList(List<? extends IProduct> list) {
        Log.d(TAG, "toModelList() start");
        List<ProductModel>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toModel(iProduct));
        }
        Log.d(TAG, "toModelList() end: " + result.toString());
        return result;
    }

    @Override
    public List<ProductDTO> toDtoList(List<? extends IProduct> list) {
        Log.d(TAG, "toDtoList() start");
        List<ProductDTO>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toDto(iProduct));
        }
        Log.d(TAG, "toDtoList() end:" + result.toString());
        return result;
    }

    @Override
    public List<ProductWithCategory> toEntityList(List<? extends IProduct> list) {
        Log.d(TAG, "toEntityList() start");
        List<ProductWithCategory>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toEntity(iProduct));
        }
        Log.d(TAG, "toEntityList() end:" + result.toString());
        return result;
    }

    @Override
    public List<Product> toEntityListProducts(List<? extends IProduct> list) {
        List<Product>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toEntity(iProduct).getProduct());
        }
        return result;
    }
}
