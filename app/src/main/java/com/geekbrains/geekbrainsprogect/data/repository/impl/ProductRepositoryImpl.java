package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.dto.FundDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
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
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProductRepositoryImpl implements ProductRepository {
    ProductDao productDao;
    ProductContractorCrossDao productContractorCrossDao;
    ProductCategoryCrossDao productCategoryCrossDao;
    ProductTransactionCrossDao productTransactionCrossDao;
    ProductTransactionDao productTransactionDao;
    FundService fundService;
    ContractorDao contractorDao;
    ProductTransactionService productTransactionService;
    ContractorService contractorService;
    ProductMapper productMapper;
    ProductTransactionMapper productTransactionMapper;
    UnitDao unitDao;
    ProductService productService;

    @Override
    public Flowable<List<ProductWithCategory>> getProductListFromDB() {
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

        return Completable.fromAction(() -> {
                Disposable disposable = productService.addProduct(productDTO)
                .flatMap(x -> fundService.getFundsByProductId(x.getId()))
                .subscribeOn(Schedulers.io())
                .subscribe(this::productSaved);});

    }

    @Override
    public Completable deleteProductById(long id) {

        return productService.deleteProductById(id)
                .andThen(productDao.deleteProductById(id));
    }

    @Override
    public Observable<String> deleteAllProduct() {

        return null;
    }

    @Override
    public Observable<ResponseBody> editProduct(Product product) {
        return null;
    }


    @Override
    public Completable getProductFromServer() {

        return fundService.getAllFunds()
                    .subscribeOn(Schedulers.io())
                    .flatMapIterable(x -> {productDao.deleteAllProduct();
                        return x;
                    })
                    .flatMapCompletable(this::productSaved);
                }


    private  Completable productSaved(FundDTO fundDTO)
    {
        return loadProductTransactions(fundDTO)
                .andThen(saveProductToDatabase(fundDTO))
                .andThen(loadContractors(fundDTO))
                .andThen(saveProductCategory(fundDTO.getCategoryList(), fundDTO.getProduct()));
    }

    private Completable loadProductTransactions(FundDTO fundDTO) {
        ProductDTO product = fundDTO.getProduct();
        return productTransactionService.getProductTransactionById(product.getId()).subscribeOn(Schedulers.io())
                .map(items -> productTransactionMapper.toEntityList(items))
                .flatMapIterable(x -> { productTransactionDao.insert(x.toArray(new ProductTransaction[0]));
                    productTransactionCrossDao.deleteByProduct(product.getId());
                    return x;
                })
                .flatMapCompletable(x -> productTransactionCrossDao.insert(new ProductTransactionCrossRef(product.getId(), x.getId())));
    }


    private Completable saveProductToDatabase(FundDTO fundDTO) {
        return productDao.insert(productMapper.toEntity(fundDTO).product);
    }

    public Completable saveProductCategory(List<Category>categories, ProductDTO productDTO)
    {
        return Observable.fromIterable(categories)
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .flatMapIterable(x -> {productCategoryCrossDao.deleteByProduct(productDTO.getId());
                return x;
                })
                .flatMapCompletable(x -> productTransactionCrossDao.insert(new ProductTransactionCrossRef(productDTO.getId(), x.getId())));
    }

    public Completable loadContractors(FundDTO fund)
    {
            ProductDTO product = fund.getProduct();
            return contractorService.getProvidersByProductId(product.getId())
                    .subscribeOn(Schedulers.io())
                    .flatMapIterable(x -> {productContractorCrossDao.deleteByProduct(fund.getProduct().getId());
                        return x;
                    })
                    .flatMapCompletable(contractor -> { contractorDao.insert(contractor);
                            return productContractorCrossDao.insert(new ProductContractorCrossRef(product.getId(), contractor.getId()));});
    }
}
