package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.ProductInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @ProductScope
    @Provides
    ProductInteractor provideProductInteractor(ProductRepository productRepository, CategoryRepository categoryRepository, UnitRepository unitRepository, ProductMapper productMapper)
    {
        return new ProductInteractorImpl(productRepository, categoryRepository, unitRepository, productMapper);
    }
}
