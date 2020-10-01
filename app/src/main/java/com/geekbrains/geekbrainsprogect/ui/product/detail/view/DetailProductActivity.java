package com.geekbrains.geekbrainsprogect.ui.product.detail.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.product.detail.presenter.DetailProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.model.Fund;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;

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


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        setData(presenter.nextProduct());
    }

    @OnClick({R.id.new_shipment_button, R.id.new_supply_button, R.id.next_product_button, R.id.prew_product_button, R.id.product_category, R.id.product_description, R.id.provider_name})
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
                setData(presenter.nextProduct());
                break;
            case R.id.prew_product_button:
                setData(presenter.prevProduct());
                break;
//            case R.id.product_category:
//                presenter.editCategoty();
//                break;
//            case R.id.product_description:
//                presenter.editDesription();
//                break;
//            case R.id.product_units:
//                presenter.editUnits();
//                break;
        }
    }

    private void setData(Fund fund)
    {
        Product product = fund.getProduct();
        productName.setText(fund.getProduct().getTitle());
        productDescription.setText(getString(R.string.description_field, product.getDescription()));
        productCategory.setText(getString(R.string.category_field, product.getCategoriesString()));
        productCount.setText(fund.getStringBalance());
        productUnits.setText(product.getUnits());
    }

    public void createDialogSupply(Product product)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product, productTransaction -> presenter.supplyToServer(productTransaction), true);
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

    public void createDialogShipment(Product product)
    {
        DialogTransaction dialogTransaction = new DialogTransaction(product, productTransaction -> presenter.shipmentToServer(productTransaction), false);
        dialogTransaction.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void setDataToContractorsTextView(String contractorsString) {
        providerName.setText(getString(R.string.provider_field, contractorsString));
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