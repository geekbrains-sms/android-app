package com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListView;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {
    public static final String TAG = "ProductListPresenter";
    ProductInteractor productInteractor;

    public ProductListPresenter(ProductInteractor productInteractor)
    {
        this.productInteractor = productInteractor;
        subscribeToDB();

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadFromServer();
    }

    public void loadFromServer() {
        Disposable disposable = productInteractor.saveProductFromServerToDB().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    Log.d(TAG, "Load Complete");
                    }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public void subscribeToDB()
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

    public void deleteProduct(List<Long> selectedProduct) {
        productInteractor.deleteProducts(selectedProduct);
    }

    public void addProduct(ProductModel product) {
        Disposable disposable = productInteractor.addProduct(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                getViewState().showToast(R.string.product_create_sucesses);
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });

    }

    public void loadAddProductDialog() {
        Disposable disposable = productInteractor.loadUnitsWithCategories()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(usersWithCategory-> {
                    getViewState().showAddProductDialog(usersWithCategory);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }
}
