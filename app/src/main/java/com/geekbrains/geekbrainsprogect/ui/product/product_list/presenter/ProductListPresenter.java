package com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.ProductModule;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.domain.interactor.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListView;
import java.util.List;
import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {
    public static final String TAG = "ProductListPresenter";
    ProductInteractor productInteractor;

    public ProductListPresenter(ProductInteractor productInteractor)
    {
        this.productInteractor = productInteractor;
        loadFromServer();
    }

    public void loadFromServer() {
//        Single<Response<List<Unit>>> single = AppData.getApiHelper().getAllUnits();
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(unitsResponse ->{
//            if(unitsResponse.isSuccessful())
//            {
//                AppData.setUnitList(unitsResponse.body());
//            }
//        }, throwable -> {
//
//        });
        Disposable disposable = productInteractor.getProductFromServer().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    Log.d(TAG, "Load Complete");
                    }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });

    }

    public void getProductList()
    {
        Flowable<List<ProductModel>> list = productInteractor.getProductListFromDB();
        Disposable disposable = list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList -> {
                getViewState().setDataToAdapter(productList);
                Log.d(TAG, "Product load from DB complete");
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
            Log.e(TAG, "Product load from DB: " + throwable.getMessage());
        });
    }

//    public void deleteProduct(List<Fund> selectedProduct) {
//
//    }
//
//    public void addProductToServer(Product product) {
//        Single<Response<Product>> single = AppData.getApiHelper().addProduct(product);
//
//        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(productResponse -> {
//            if(productResponse.isSuccessful())
//            {
//                Fund fund = new Fund(productResponse.body());
//                AppData.getProductList().add(fund);
//                getViewState().setDataToAdapter();
//                getViewState().showToast(R.string.product_create_sucesses);
//            }
//            else
//            {
//                getViewState().showAlertDialog(productResponse.errorBody().string());
//            }
//        }, throwable -> {
//            getViewState().showAlertDialog(throwable.getMessage());
//        });

//    }
}
