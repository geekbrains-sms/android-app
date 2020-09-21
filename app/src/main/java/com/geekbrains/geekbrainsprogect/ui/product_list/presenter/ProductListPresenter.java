package com.geekbrains.geekbrainsprogect.ui.product_list.presenter;

import android.util.Log;

import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product_list.view.IRecyclerProduct;
import com.geekbrains.geekbrainsprogect.ui.product_list.view.IViewHolderProduct;
import com.geekbrains.geekbrainsprogect.ui.product_list.view.ProductListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
@InjectViewState
public class ProductListPresenter extends MvpPresenter<ProductListView> {
    public static final String TAG = "ProductListPresenter";

    private List<Product> productList = new ArrayList<>();
    RecyclerProduct recyclerProduct;

    public ProductListPresenter()
    {
        recyclerProduct = new RecyclerProduct();
        getProductList();
    }

    public RecyclerProduct getRecyclerProduct() {
        return recyclerProduct;
    }

    private void getProductList()
    {
        Single<List<Product>> single = AppData.getApiHelper().getProductList();
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(products ->
        {
            productList = products;
            Log.e(TAG, "loadFromServer: " + productList.toString());
            getViewState().refreshRecyclerView();
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
            Log.e(TAG, "loadFromServerError: " + throwable.getMessage());
        });
    }

    private class RecyclerProduct implements IRecyclerProduct
    {
        @Override
        public void bindView(IViewHolderProduct iViewHolder) {
            iViewHolder.bind(productList.get(iViewHolder.getPos()));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }
}
