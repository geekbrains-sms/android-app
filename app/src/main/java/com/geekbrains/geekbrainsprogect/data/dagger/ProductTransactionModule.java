package com.geekbrains.geekbrainsprogect.data.dagger;

import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapperImpl;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapperImpl;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;


import dagger.Module;
import dagger.Provides;
@Module
public class ProductTransactionModule {
    @ProductScope
    @Provides
    ProductTransactionMapper provideProductMapper()
    {
        return new ProductTransactionMapperImpl();
    }
}
