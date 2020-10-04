package com.geekbrains.geekbrainsprogect.ui.product.product_list.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListView;
import java.util.List;
import java.util.Objects;
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

    public ProductListPresenter()
    {
        getProductList();
        getCategoryList();
        loadUnitsFromServer();
    }

    private void getCategoryList()
    {
        Single<Response<List<Category>>> single = AppData.getApiHelper().getCategoryList();
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categoryResponse ->{
            if(categoryResponse.isSuccessful())
            {
                AppData.setCategoryList(categoryResponse.body());
            }

        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }

    private void loadUnitsFromServer() {
        Single<Response<List<Unit>>> single = AppData.getApiHelper().getAllUnits();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(unitsResponse ->{
            if(unitsResponse.isSuccessful())
            {
                AppData.setUnitList(unitsResponse.body());
            }
        }, throwable -> {

        });
    }

    private void getProductList()
    {
        Single<Response<List<Fund>>> single = AppData.getApiHelper().getAllFunds();
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(fundsResponse ->
        {
            if(fundsResponse.isSuccessful())
            {
                AppData.setProductList(fundsResponse.body());
                Log.e(TAG, "loadFromServer: " + fundsResponse.toString());
                getViewState().setDataToAdapter(AppData.getProductList());
            }
            else
            {
                getViewState().showAlertDialog(Objects.requireNonNull(fundsResponse.errorBody()).string());
            }

        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
            Log.e(TAG, "loadFromServerError: " + throwable.getMessage());
        });
    }

    public void deleteProduct(List<Fund> selectedProduct) {

    }

    public void addProductToServer(Product product) {
        Single<Response<Product>> single = AppData.getApiHelper().addProduct(product);

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(productResponse -> {
            if(productResponse.isSuccessful())
            {
                Fund fund = new Fund(productResponse.body());
                AppData.getProductList().add(fund);
                getViewState().updateDisplay();
                getViewState().showToast(R.string.product_create_sucesses);
            }
            else
            {
                getViewState().showAlertDialog(productResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });


    }
}
