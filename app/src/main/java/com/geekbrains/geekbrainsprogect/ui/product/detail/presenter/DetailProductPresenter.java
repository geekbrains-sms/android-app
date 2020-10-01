package com.geekbrains.geekbrainsprogect.ui.product.detail.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.Model;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductView;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class DetailProductPresenter extends MvpPresenter<DetailProductView> {
    private static final String TAG = "DetailProductPresenter";
    Model model;
    public DetailProductPresenter()
    {
        model = new Model();
        if(AppData.getContractorList() == null)
        {
            loadProvidersList();
        }
    }

    public Fund nextProduct()
    {
        int count = model.getPage() + 1;
        return updatePage(count);
    }
    public Fund prevProduct() {
        int count = model.getPage() - 1;
        return updatePage(count);
    }

    private Fund updatePage(int count) {
        model.setPage(count);
        Fund fund = AppData.getSelectedProducts().get(count);
        arrowControl();
        contractorsListControl(fund);
        return fund;
    }



    private void contractorsListControl(Fund fund) {
        if(fund.getProduct().getContractors() == null)
        {
            loadProvidersByProduct(fund.getProduct());
        }
        else
        {
            getViewState().setDataToContractorsTextView(fund.getProduct().getContractorsString());
        }
    }

    private void loadProvidersByProduct(Product product) {
        Single<Response<List<Contractor>>> single = AppData.getApiHelper().getProvidersByProductId(product.getId());

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(contractorsResponse ->{
            if(contractorsResponse.isSuccessful())
            {
                product.setContractors(contractorsResponse.body());
                getViewState().setDataToContractorsTextView(product.getContractorsString());
            }
        }, throwable -> {

        });
    }

    private void arrowControl() {
        if(model.getPage() == AppData.getSelectedProducts().size() - 1)
        {
            getViewState().rightArrowVisibility(false);
        }
        else
        {
            getViewState().rightArrowVisibility(true);
        }

        if(model.getPage() == 0)
        {
            getViewState().leftArrowVisibility(false);
        }
        else
        {
            getViewState().leftArrowVisibility(true);
        }
    }

    public void supply() {
        getViewState().createDialogSupply(AppData.getSelectedProducts().get(model.getPage()).getProduct());
    }

    private void loadProvidersList() {
        Single<Response<List<Contractor>>> single = AppData.getApiHelper().getAllContractors();

        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(contractorsResponse ->{
            if(contractorsResponse.isSuccessful())
            {
                AppData.setContractorList(contractorsResponse.body());
            }
        }, throwable -> {

        });
    }

    public void supplyToServer(ProductTransaction productTransaction) {
        Single<Response<List<ProductTransaction>>> single = AppData.getApiHelper().addSupplyTransactions(productTransaction);
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(contractorsResponse ->{
            if(contractorsResponse.isSuccessful())
            {
                Log.d(TAG, "supplyResponce: " + contractorsResponse.body());
                AppData.setSupplyTransactions(contractorsResponse.body());
            }
            else
            {
                Log.d(TAG, "supplyResponce: " + contractorsResponse.errorBody());
            }
        }, throwable -> {
            Log.d(TAG, "supplyResponce: " + throwable.getMessage());
        });

    }

    public void shipment() {
        getViewState().createDialogShipment(AppData.getSelectedProducts().get(model.getPage()).getProduct());
    }

    public void shipmentToServer(ProductTransaction productTransaction) {
        Single<Response<List<ProductTransaction>>> single = AppData.getApiHelper().addShipmentTransactions(productTransaction);
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(shipmentResponse ->{
            if(shipmentResponse.isSuccessful())
            {
                AppData.setShipmentTransactions(shipmentResponse.body());
                Log.d(TAG, "shipmentResponce: " + shipmentResponse.body());
            }
            else
            {
                Log.d(TAG, "shipmentResponce: " + shipmentResponse.errorBody());
            }
        }, throwable -> {
            Log.d(TAG, "shipmentResponce: " + throwable.getMessage());
        });
    }
}
