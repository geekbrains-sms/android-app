package com.geekbrains.geekbrainsprogect.ui.product.detail.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.DetailProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.Model;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductView;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.model.ProductListModel;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class DetailProductPresenter extends MvpPresenter<DetailProductView> {
    private static final String TAG = "DetailProductPresenter";
    Model model;
    DetailProductInteractor detailProductInteractor;
    @Inject
    public DetailProductPresenter(DetailProductInteractor detailProductInteractor)
    {
        this.detailProductInteractor = detailProductInteractor;
        model = new Model();
        loadSelectedProductFromDB();
    }

    private void loadSelectedProductFromDB() {
        Disposable disposable = detailProductInteractor.subscribeToProductChangesById(ProductListModel.getSelectedProductList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList -> {
                    model.setProductList(productList);
                    getViewState().updatePage(getCurrentProduct());
                    arrowControl();

                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public ProductModel nextProduct()
    {
        int count = model.getPage() + 1;
        refreshPage(count);
        return getCurrentProduct();
    }
    public ProductModel prevProduct() {
        int count = model.getPage() - 1;
        refreshPage(count);
        return getCurrentProduct();
    }

    private ProductModel getCurrentProduct() {
        ProductModel productModel = model.getProductList().get(model.getPage());
        visibilityChangeButton(productModel.isChanged());
        return productModel;
    }

    private void refreshPage(int count)
    {
        model.setPage(count);
        arrowControl();
    }



    private void arrowControl() {
        if(model.getPage() == ProductListModel.getSelectedProductList().size() - 1)
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
//
    public void supply() {
        Disposable disposable = detailProductInteractor.getContractorsFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contractors -> {
                    getViewState().showDialogSupply(getCurrentProduct(), contractors);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public void transactionToServer(ProductTransactionModel productTransaction) {
        Disposable disposable = detailProductInteractor.addProductTransaction(productTransaction, getCurrentProduct().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{
                getViewState().showToast(R.string.transaction_sucesses);
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });

    }
    public void shipment() {
        Disposable disposable = detailProductInteractor.getContractorsFromDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contractors -> {
                    getViewState().showDialogShipment(getCurrentProduct(), contractors);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });

    }
    public void editProduct()
    {
        Disposable disposable = detailProductInteractor.editProduct(getCurrentProduct())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                getViewState().updatePage(getCurrentProduct());
                getViewState().showToast(R.string.edit_sucsses);

        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }


    public void setEditFlag(boolean flag) {
        ProductModel productModel = getCurrentProduct();
        productModel.setChanged(flag);
        visibilityChangeButton(flag);
    }

    private void visibilityChangeButton(boolean flag) {
        getViewState().setVisibilityChangedButton(flag);
    }

    public void createEditDialog() {
        Disposable disposable = detailProductInteractor.getEditProductData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(editProductData->{
                    getViewState().showEditDialog(getCurrentProduct(), editProductData);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public void loadTransactions() {
        Disposable disposable = detailProductInteractor.getTransactionsByProduct(getCurrentProduct().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productTransactionModels -> {
                    getViewState().showTransactionListDialog(productTransactionModels, getCurrentProduct());
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }
}
