package com.geekbrains.geekbrainsprogect.data.dagger;

import android.app.Application;

import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;

import java.util.List;


public class AppData extends Application {
    private static AppComponent appComponent;
    private static User currentUser;
    private static List<Fund> selectedProducts;
    private static List<Fund> productList;
    private static List<Contractor> contractorList;
    private static List<ProductTransaction> supplyTransactions;
    private static List<ProductTransaction> shipmentTransactions;
    private static List<Unit>unitList;
    private static ApiHelper apiHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();
        appComponent.inject(this);
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent generateAppComponent()
    {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static void setApiHelper(ApiHelper helper) {
        apiHelper = helper;
    }

    public static ApiHelper getApiHelper() {
        return apiHelper;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static List<Fund> getSelectedProducts() {
        return selectedProducts;
    }

    public static void setSelectedProducts(List<Fund> selectedProducts) {
        AppData.selectedProducts = selectedProducts;
    }

    public static List<Contractor> getContractorList() {
        return contractorList;
    }

    public static void setContractorList(List<Contractor> contractorList) {
        AppData.contractorList = contractorList;
    }

    public static List<ProductTransaction> getShipmentTransactions() {
        return shipmentTransactions;
    }

    public static List<ProductTransaction> getSupplyTransactions() {
        return supplyTransactions;
    }

    public static void setShipmentTransactions(List<ProductTransaction> shipmentTransactions) {
        AppData.shipmentTransactions = shipmentTransactions;
    }

    public static void setSupplyTransactions(List<ProductTransaction> supplyTransactions) {
        AppData.supplyTransactions = supplyTransactions;
    }

    public static void setProductList(List<Fund> productList) {
        AppData.productList = productList;
    }

    public static List<Fund> getProductList() {
        return productList;
    }

    public static List<Unit> getUnitList() {
        return unitList;
    }

    public static void setUnitList(List<Unit> unitList) {
        AppData.unitList = unitList;
    }

    public static void updateFund(Fund oldFund, Fund newFund)
    {
        if(productList != null)
        {
            productList.set(productList.indexOf(oldFund), newFund);
        }
        if(selectedProducts != null)
        {
            selectedProducts.set(selectedProducts.indexOf(oldFund), newFund);
        }
    }

    public static void updateContractors(Contractor old, Contractor newContractor)
    {
        if(contractorList != null)
        {
            contractorList.set(contractorList.indexOf(old), newContractor);
        }
    }
}
