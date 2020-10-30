package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.DetailProductInteractor;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseActivity;
import com.geekbrains.geekbrainsprogect.ui.product.detail.model.EditProductData;
import com.geekbrains.geekbrainsprogect.ui.product.detail.presenter.DetailProductPresenter;
import com.geekbrains.geekbrainsprogect.data.model.entity.Product;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class DetailProductActivity extends BaseActivity implements DetailProductView {
    private static final String TAG = "DetailProductActivity";
    @InjectPresenter
    DetailProductPresenter presenter;
    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_category)
    TextView productCategory;
    @BindView(R.id.product_description)
    TextView productDescription;
    @BindView(R.id.provider_name)
    TextView providerName;
    @BindView(R.id.product_count)
    TextView productCount;
    @BindView(R.id.product_units)
    TextView productUnits;
    @BindView(R.id.prew_product_button)
    ImageView leftArrow;
    @BindView(R.id.next_product_button)
    ImageView rightArrow;
    @BindView(R.id.save_edit_product)
    Button saveProduct;
    @Inject
    DetailProductInteractor detailProductInteractor;
    @ProvidePresenter
    DetailProductPresenter providePresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new DetailProductPresenter(detailProductInteractor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.new_shipment_button, R.id.new_supply_button, R.id.next_product_button, R.id.prew_product_button, R.id.transactions_dialog_button, R.id.product_name, R.id.save_edit_product})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.new_shipment_button:
                presenter.shipment();
                break;
            case R.id.new_supply_button:
                presenter.supply();
                break;
            case R.id.next_product_button:
                updatePage(presenter.nextProduct());
                break;
            case R.id.prew_product_button:
                updatePage(presenter.prevProduct());
                break;
            case R.id.transactions_dialog_button:
                presenter.loadTransactions();
                break;
            case R.id.product_name:
                presenter.createEditDialog();

            case R.id.save_edit_product:
                presenter.editProduct();
                break;
        }
    }


    public void updatePage(ProductModel product)
    {

        productName.setText(product.getTitle());
        productDescription.setText(getString(R.string.description_field, product.getDescription()));
        productCategory.setText(getString(R.string.category_field, product.getCategoriesString()));
        productCount.setText(product.getStringQuantity());
        productUnits.setText(product.getUnit().getTitle());
    }

    @Override
    public void showEditDialog(ProductModel currentProduct, EditProductData editProductData) {
        DialogFragment dialog = new EditProductDialog(currentProduct, editProductData, product -> {
            presenter.setEditFlag(true);
            updatePage(product);
        });
        dialog.show(getSupportFragmentManager(), TAG);
    }

    //
    public void showDialogSupply(ProductModel product, List<Contractor>contractors)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product, contractors, DialogTransaction.TYPE_SUPPLY, productTransaction -> presenter.transactionToServer(productTransaction));
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

    public void showDialogShipment(ProductModel product, List<Contractor>contractors)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product,contractors, DialogTransaction.TYPE_SHIPMENT, productTransaction -> presenter.transactionToServer(productTransaction));
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

//    @Override
//    public void setDataToContractorsTextView(String contractorsString) {
//        providerName.setText(getString(R.string.provider_field, contractorsString));
//    }

    @Override
    public void showTransactionListDialog(List<ProductTransactionModel> body) {
        TransactionsListDialog dialog = new TransactionsListDialog(body);
        dialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void setVisibilityChangedButton(boolean flag) {
        setViewVisibility(saveProduct, flag);
    }



    @Override
    public void leftArrowVisibility(boolean visibility) {
        setViewVisibility(leftArrow, visibility);
    }

    @Override
    public void rightArrowVisibility(boolean visibility) {
       setViewVisibility(rightArrow, visibility);
    }

    private void setViewVisibility(View view, boolean visibility)
    {
        if(visibility)
        {
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            view.setVisibility(View.INVISIBLE);
        }
    }

}