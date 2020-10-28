package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.impl.ProductMapperImpl;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.impl.ProductTransactionMapperImpl;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.UserMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.impl.UserMapperImpl;

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
    ProductTransactionMapper provideProductTransactionMapper(UserMapper userMapper)
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
