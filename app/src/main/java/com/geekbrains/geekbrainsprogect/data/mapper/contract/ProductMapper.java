package com.geekbrains.geekbrainsprogect.data.mapper.contract;

import com.geekbrains.geekbrainsprogect.data.api.model.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductDTO, ProductWithCategory, ProductModel, IProduct> {
    List<Product>toEntityListProducts(List<? extends IProduct>list);

}
