package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapperImpl;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapperImpl;
import com.geekbrains.geekbrainsprogect.data.mapper.UserMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.UserMapperImpl;

import dagger.Module;
import dagger.Provides;
@Module
public class MapperModule {
    @ProductScope
    @Provides
    ProductMapper provideProductMapper(ProductTransactionMapper mapper)
    {
        return new ProductMapperImpl(mapper);
    }

    @ProductScope
    @Provides
    ProductTransactionMapper provideProductMapper(UserMapper userMapper)
    {
        return new ProductTransactionMapperImpl(userMapper);
    }

    @ProductScope
    @Provides
    UserMapper provideUserMapper()
    {
        return new UserMapperImpl();
    }
}
