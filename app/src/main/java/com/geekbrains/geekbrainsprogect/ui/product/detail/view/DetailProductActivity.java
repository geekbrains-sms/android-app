package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.detail.presenter.DetailProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.ProductTransaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class DetailProductActivity extends MvpAppCompatActivity implements DetailProductView {
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


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        updatePage(presenter.nextProduct());
    }

    @OnClick({R.id.new_shipment_button, R.id.new_supply_button, R.id.next_product_button, R.id.prew_product_button, R.id.product_category, R.id.product_description, R.id.provider_name, R.id.transactions_dialog_button, R.id.product_name, R.id.save_edit_product, R.id.product_units})
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
                productNameEdit(presenter.getProduct());
            case R.id.product_category:
//               editCategoty(presenter.getProduct());
                break;
            case R.id.product_description:
                editDescription(presenter.getProduct());
                break;
            case R.id.product_units:
                presenter.editUnits();
                break;
            case R.id.save_edit_product:
                presenter.saveChangesProduct();
                break;
        }
    }


    public void updatePage(Fund fund)
    {
        Product product = fund.getProduct();
        productName.setText(fund.getProduct().getTitle());
        productDescription.setText(getString(R.string.description_field, product.getDescription()));
        productCategory.setText(getString(R.string.category_field, product.getCategoriesString()));
        productCount.setText(fund.getStringBalance());
        productUnits.setText(product.getUnitsTitle());
    }

    public void createDialogSupply(Product product)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product, productTransaction -> presenter.supplyToServer(productTransaction));
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

    public void createDialogShipment(Product product)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product, productTransaction -> presenter.shipmentToServer(productTransaction));
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void setDataToContractorsTextView(String contractorsString) {
        providerName.setText(getString(R.string.provider_field, contractorsString));
    }

    @Override
    public void showTransactionListDialog(List<ProductTransaction> body) {
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

    private void productNameEdit(Product product)
    {
        EditDialog editDialog = new EditDialog(product, EditDialog.PRODUCT_NAME, product1 -> {
            presenter.setEditFlag(true);
            updatePage(presenter.getFund());
        });
        editDialog.show(getSupportFragmentManager(), TAG);
    }
    private void editDescription(Product product) {
        EditDialog editDialog = new EditDialog(product, EditDialog.PRODUCT_DESCRIPTION, product1 -> {
            presenter.setEditFlag(true);
            updatePage(presenter.getFund());
        });
        editDialog.show(getSupportFragmentManager(), TAG);
    }
    @Override
    public void showEditUnitsDialog(Product product) {
        EditDialog editDialog = new EditDialog(product, EditDialog.PRODUCT_UNITS, product1 -> {
            presenter.setEditFlag(true);
            updatePage(presenter.getFund());
        });
        editDialog.show(getSupportFragmentManager(), TAG);

    }

    @Override
    public void showToast(int stringResource) {
        Toast.makeText(getApplicationContext(), stringResource,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorDialog(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle(R.string.error);
        builder.setMessage(error);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }

}