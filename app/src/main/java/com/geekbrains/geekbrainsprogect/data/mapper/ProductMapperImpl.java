package com.geekbrains.geekbrainsprogect.data.mapper;

import com.geekbrains.geekbrainsprogect.data.api.dto.Image;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.Unit;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductMapperImpl implements ProductMapper {
    private ProductTransactionMapperImpl productTransactionMapperImpl;

    @Inject
    public ProductMapperImpl(ProductTransactionMapperImpl productTransactionMapperImpl)
    {
        this.productTransactionMapperImpl = productTransactionMapperImpl;
    }
    @Override
    public ProductModel toModel(IProduct object) {
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        Unit unit = object.getUnit();
        double quantity = object.getQuantity();
        List<ProductTransactionModel> transactions = productTransactionMapperImpl.toModelList(object.getProductTransactions());
        List<Contractor>contractors = object.getContractors();
        List<Category>categories = object.getCategoryList();
        return new ProductModel(id,title,description,unit,imageUrl,categories,contractors,transactions, quantity);
    }

    @Override
    public ProductDTO toDto(IProduct object) {
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        List<Category>categories = object.getCategoryList();
        Unit unit = object.getUnit();
        Image image = new Image(0L,null, imageUrl);

        return new ProductDTO(id,title,categories,unit, image,description);
    }

    @Override
    public ProductWithCategory toEntity(IProduct object) {
        long id = object.getId();
        String title = object.getTitle();
        String description = object.getDescription();
        String imageUrl = object.getImagePath();
        long unitId = object.getUnit().getId();
        double quantity = object.getQuantity();
        List<ProductTransaction> transactions = productTransactionMapperImpl.toEntityList(object.getProductTransactions());
        List<Contractor>contractors = object.getContractors();
        List<Category>categories = object.getCategoryList();
        Unit unit = object.getUnit();

        Product product = new Product(id,title,description,unitId,quantity, imageUrl);
        return new ProductWithCategory(product,categories,contractors,
                productTransactionMapperImpl.toEntityList(transactions),unit);
    }

    @Override
    public List<ProductModel> toModelList(List<? extends IProduct> list) {
        List<ProductModel>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toModel(iProduct));
        }
        return result;
    }

    @Override
    public List<ProductDTO> toDtoList(List<? extends IProduct> list) {
        List<ProductDTO>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toDto(iProduct));
        }
        return result;
    }

    @Override
    public List<ProductWithCategory> toEntityList(List<? extends IProduct> list) {
        List<ProductWithCategory>result = new ArrayList<>();
        for(IProduct iProduct : list)
        {
            result.add(toEntity(iProduct));
        }
        return result;
    }
}
