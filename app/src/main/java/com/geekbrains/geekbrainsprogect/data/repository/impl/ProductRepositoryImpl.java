package com.geekbrains.geekbrainsprogect.data.repository.impl;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.api.dto.FundDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.CategoryDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductContractorCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.UnitDao;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import java.util.List;
import java.util.Observer;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProductRepositoryImpl implements ProductRepository {
    private static final String TAG = "ProductRepository";
    ProductDao productDao;
    ProductContractorCrossDao productContractorCrossDao;
    ProductCategoryCrossDao productCategoryCrossDao;
    ProductTransactionCrossDao productTransactionCrossDao;
    ProductTransactionDao productTransactionDao;
    FundService fundService;
    ContractorDao contractorDao;
    CategoryDao categoryDao;
    ProductTransactionService productTransactionService;
    ContractorService contractorService;
    ProductMapper productMapper;
    ProductTransactionMapper productTransactionMapper;
    UnitDao unitDao;
    ProductService productService;
    @Inject
    public ProductRepositoryImpl(ProductDao productDao, ProductContractorCrossDao productContractorCrossDao,
                                 ProductCategoryCrossDao productCategoryCrossDao, ProductTransactionCrossDao productTransactionCrossDao,
                                 ProductTransactionDao productTransactionDao, FundService fundService, ContractorDao contractorDao,
                                 ProductTransactionService productTransactionService, ContractorService contractorService,
                                 ProductMapper productMapper, ProductTransactionMapper productTransactionMapper, UnitDao unitDao, CategoryDao categoryDao,
                                 ProductService productService) {
        this.productDao = productDao;
        this.productContractorCrossDao = productContractorCrossDao;
        this.productCategoryCrossDao = productCategoryCrossDao;
        this.productTransactionCrossDao = productTransactionCrossDao;
        this.productTransactionDao = productTransactionDao;
        this.fundService = fundService;
        this.contractorDao = contractorDao;
        this.productTransactionService = productTransactionService;
        this.contractorService = contractorService;
        this.productMapper = productMapper;
        this.productTransactionMapper = productTransactionMapper;
        this.unitDao = unitDao;
        this.productService = productService;
        this.categoryDao = categoryDao;
    }

    @Override
    public Flowable<List<ProductWithCategory>> getProductListFromDB() {
        Log.d(TAG, "LoadProductFromDB");
        return productDao.getAllProduct()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(x -> x)
                .flatMap(productWithCategory -> unitDao.getUnitById(productWithCategory.product.getIdUnit()),
                        (productWithCategory, unit)-> {productWithCategory.setUnit(unit);
                            return productWithCategory;})
                .toList()
                .toFlowable();
    }

    @Override
    public Completable addProduct(ProductModel product) {
        ProductDTO productDTO = productMapper.toDto(product);
        Log.d(TAG, "addProduct()");
        return Completable.fromAction(() -> {
                Disposable disposable = productService.addProduct(productDTO)
                .flatMap(x -> fundService.getFundsByProductId(x.getId()))
                .subscribeOn(Schedulers.io())
                .subscribe(this::productSaved);});
    }

    @Override
    public void deleteProductById(long id) {
        Log.d(TAG, "deleteProductById()");
       productService.deleteProductById(id);
    }

    @Override
    public void deleteAllProduct() {
        productDao.deleteAllProduct();
        Log.d(TAG, " deleteAllProduct()");
    }

    @Override
    public Observable<ResponseBody> editProduct(Product product) {
        return null;
    }


    @Override
    public Completable getProductFromServer() {
        Log.d(TAG, "getProductFromServer()");
        return fundService.getAllFunds()
                    .doOnNext(x -> deleteAllProduct())
                    .flatMap(Observable::fromIterable)
                    .doOnNext(this::saveProductToDatabase)
                    .flatMapCompletable(this::productSaved);
                }

    private  Completable productSaved(FundDTO fundDTO)
    {
        Log.d(TAG, "startProductSaved(): product id - " + fundDTO.getProduct().getId());
       return Completable.mergeArray(loadProductTransactions(fundDTO), saveProductCategory(fundDTO), loadContractors(fundDTO));
    }

    private Completable loadProductTransactions(FundDTO fundDTO) {

        ProductDTO product = fundDTO.getProduct();
        return productTransactionService.getProductTransactionById(product.getId())
                .map(items -> productTransactionMapper.toEntityList(items))
                .doOnNext(x -> productTransactionCrossDao.deleteAll())
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x -> Completable.fromRunnable(() -> {productTransactionCrossDao.insert(new ProductTransactionCrossRef(product.getId(),x.getId()));
                    productTransactionDao.insert(x);
                    Log.d(TAG, "loadProductTransactions: product id - " + fundDTO.getProduct().getId() + ", productTransactionID - " + x.getId());
                }));

    }

    private void saveProductToDatabase(FundDTO fundDTO) {
        Log.d(TAG, "saveProductToDatabase() start");
        productDao.insert(productMapper.toEntity(fundDTO).product);
    }
    public Completable saveProductCategory(FundDTO fundDTO)
    {
        return Completable.fromRunnable(()->{
            productCategoryCrossDao.deleteByProduct(fundDTO.getProduct().getId());
            for(Category category: fundDTO.getCategoryList())
           {
               Log.d(TAG, "saveCategoryToDatabase() start");
               categoryDao.insert(category);
               productCategoryCrossDao.insert(new ProductCategoryCrossRef(fundDTO.getId(), category.getId()));
           }
        });
    }

    public Completable loadContractors(FundDTO fund) {
        Log.d(TAG, "loadContractors() start");
        return contractorService.getProvidersByProductId(fund.getProduct().getId())
                .doOnNext(x -> productContractorCrossDao.deleteByProduct(fund.getProduct().getId()))
                .flatMap(Observable::fromIterable)
                .flatMapCompletable(x ->
                        Completable.fromRunnable(() -> {
                            contractorDao.insert(x);
                            productContractorCrossDao.insert(new ProductContractorCrossRef(fund.getProduct().getId(), x.getId()));
                            Log.d(TAG, "loadContractors product id - " + fund.getProduct().getId());
                        }));
    }
}
