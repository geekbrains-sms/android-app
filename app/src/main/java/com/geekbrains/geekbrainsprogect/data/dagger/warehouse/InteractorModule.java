package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserActionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UserRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.CategoryInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.DetailProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserActionInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.CategoryInteractorImpl;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.ContractorInteractorImpl;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.DetailProductInteractorImpl;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.ProductInteractorImpl;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.UserActionInteractorImpl;
import com.geekbrains.geekbrainsprogect.domain.interactor.impl.UserInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @ProductScope
    @Provides
    ProductInteractor provideProductInteractor(ProductRepository productRepository, CategoryRepository categoryRepository,
                                               ContractorRepository contractorRepository, UnitRepository unitRepository,
                                               ProductTransactionRepository productTransactionRepository, ProductMapper productMapper)
    {
        return new ProductInteractorImpl(productRepository, categoryRepository, contractorRepository,unitRepository, productTransactionRepository,productMapper);
    }
    @ProductScope
    @Provides
    DetailProductInteractor provideProductDetailInteractor(ProductRepository productRepository,
                                                           ProductTransactionRepository productTransactionRepository,
                                                           ProductMapper productMapper, ProductTransactionMapper productTransactionMapper,
                                                           CategoryRepository categoryRepository, UnitRepository unitRepository, ContractorRepository contractorRepository)
    {
        return new DetailProductInteractorImpl(productRepository, productTransactionRepository, productMapper,
                productTransactionMapper, categoryRepository,unitRepository,contractorRepository);
    }

    @ProductScope
    @Provides
    CategoryInteractor provideCategoryInteractor(CategoryRepository categoryRepository)
    {
        return new CategoryInteractorImpl(categoryRepository);
    }
    @ProductScope
    @Provides
    ContractorInteractor provideContractorInteractor(ContractorRepository contractorRepository)
    {
        return new ContractorInteractorImpl(contractorRepository);
    }
    @ProductScope
    @Provides
    UserInteractor provideUserInteractor(UserRepository userRepository)
    {
        return new UserInteractorImpl(userRepository);
    }
    @ProductScope
    @Provides
    UserActionInteractor provideUserActionInteractor(UserActionRepository userActionRepository)
    {
        return new UserActionInteractorImpl(userActionRepository);
    }

}
