package com.geekbrains.geekbrainsprogect.data.repository.impl;

import com.geekbrains.geekbrainsprogect.data.api.dto.FundDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.api.service.ContractorService;
import com.geekbrains.geekbrainsprogect.data.api.service.FundService;
import com.geekbrains.geekbrainsprogect.data.api.service.ProductTransactionService;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ContractorDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductCategoryCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductContractorCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionCrossDao;
import com.geekbrains.geekbrainsprogect.data.database.room.dao.ProductTransactionDao;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductMapper;
import com.geekbrains.geekbrainsprogect.data.mapper.ProductTransactionMapper;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductCategoryCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductContractorCrossRef;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductTransactionCrossRef;
import com.geekbrains.geekbrainsprogect.data.repository.contract.ProductRepository;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProductRepositoryIml implements ProductRepository {
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

    @Override
    public Flowable<List<ProductModel>> getProductList() {
        return null;
    }

    @Override
    public Observable<ProductModel> addProduct(Product product) {
        return null;
    }

    @Override
    public Observable<String> deleteProductById(long id) {
        return null;
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

        return Completable.fromAction(()->{
            Observable<List<FundDTO>> observable = fundService.getAllFunds();
            Disposable disposable = observable
                    .subscribeOn(Schedulers.io())
                    .subscribe(funds -> {
                        for(FundDTO fundDTO: funds)
                        {
                            for(Category category: fundDTO.getProduct().getCategoryList())
                                productCategoryCrossDao.insert(new ProductCategoryCrossRef(fundDTO.getProduct().getId(), category.getId()));
                            loadContractors(fundDTO);
                            saveProductToDatabase(fundDTO);
                            loadProductTransactions(fundDTO);
                        }
                    });
                }
        );
    }

    private void loadProductTransactions(FundDTO fundDTO) {
        ProductDTO product = fundDTO.getProduct();
        Observable<List<ProductTransactionDTO>>transactionList = productTransactionService.getProductTransactionById(product.getId());
        Disposable disposable = transactionList
                .subscribeOn(Schedulers.io())
                .map(items -> productTransactionMapper.toEntityList(items))
                .map(items -> items.toArray(new ProductTransaction[0])
                )
                .subscribe(transactions -> {
                    productTransactionDao.insert(transactions);
                    for(ProductTransaction productTransaction: transactions)
                    {
                        productTransactionCrossDao.insert(new ProductTransactionCrossRef(productTransaction.getProductId(), productTransaction.getId()));
                    }
                });
    }

    private void saveProductToDatabase(FundDTO fundDTO) {
        productDao.insert(productMapper.toEntity(fundDTO).product);
    }

    public void loadContractors(FundDTO fund)
    {
            ProductDTO product = fund.getProduct();
            Observable<List<Contractor>>contractorList = contractorService.getProvidersByProductId(product.getId());
            Disposable disposable = contractorList
                    .subscribeOn(Schedulers.io())
                    .subscribe(contractors -> {
                        for(Contractor contractor: contractors)
                        {
                            contractorDao.insert(contractor);
                            productContractorCrossDao.insert(new ProductContractorCrossRef(product.getId(), contractor.getId()));
                        }
                    });
    }
}
