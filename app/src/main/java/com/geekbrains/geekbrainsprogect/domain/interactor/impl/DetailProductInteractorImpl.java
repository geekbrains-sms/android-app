package com.geekbrains.geekbrainsprogect.domain.interactor.impl;

import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.contract.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.CategoryRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ContractorRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductTransactionRepository;
import com.geekbrains.geekbrainsprogect.data.repository.contract.UnitRepository;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.DetailProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.EditProductData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class DetailProductInteractorImpl implements DetailProductInteractor {
    ProductRepository productRepository;
    ProductTransactionRepository productTransactionRepository;
    ProductMapper productMapper;
    ProductTransactionMapper productTransactionMapper;
    CategoryRepository categoryRepository;
    UnitRepository unitRepository;
    ContractorRepository contractorRepository;
    @Inject
    public DetailProductInteractorImpl(ProductRepository productRepository,
                                       ProductTransactionRepository productTransactionRepository,
                                       ProductMapper productMapper, ProductTransactionMapper productTransactionMapper,
                                       CategoryRepository categoryRepository, UnitRepository unitRepository,
                                       ContractorRepository contractorRepository) {

        this.productRepository = productRepository;
        this.productTransactionRepository = productTransactionRepository;
        this.productMapper = productMapper;
        this.productTransactionMapper = productTransactionMapper;
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
        this.contractorRepository = contractorRepository;
    }

    @Override
    public Completable addProductTransaction(ProductTransactionModel productTransactionModel) {
        return productTransactionRepository.addProductTransactions(productTransactionModel);
    }

    @Override
    public Completable editProduct(ProductModel productModel) {
        return productRepository.editProduct(productModel)
                .flatMapCompletable(x -> saveProductCategory(x).mergeWith(saveProductUnit(x)));
    }

    @Override
    public Flowable<List<ProductModel>> subscribeToProductChangesById(List<Long> productIdList) {
        return productRepository.getProductListFromDbByIds(productIdList)
                .map(x -> productMapper.toModelList(x));
    }

    @Override
    public Flowable<EditProductData> getEditProductData() {
        return Flowable.zip(categoryRepository.getAllCategoriesFromBD(), unitRepository.getAllUnitFromBD(), EditProductData::new);
    }

    @Override
    public Completable saveContractorsFromServer() {
        return contractorRepository.saveContractorFromServerToDB();
    }

    @Override
    public Flowable<List<Contractor>> getContractorsFromDB() {
        return saveContractorsFromServer().andThen(contractorRepository.getAllContractors());
    }

    @Override
    public Flowable<List<ProductTransactionModel>> getTransactionsByProduct(long id) {
        return productRepository.getProductFromDbById(id)
                .map(x -> productTransactionMapper.toModelList(x.getProductTransactions()));
    }

    private Completable saveProductCategory(ProductWithCategory productWithCategory)
    {
        return categoryRepository.addCategoryCross(productWithCategory);
    }
    private Completable saveProductUnit(ProductWithCategory productWithCategory)
    {
        return unitRepository.addUnitToDB(productWithCategory);
    }
}
