package com.geekbrains.geekbrainsprogect.data.mapper;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductDTO, ProductWithCategory, ProductModel, IProduct> {

}
