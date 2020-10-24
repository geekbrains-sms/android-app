package com.geekbrains.geekbrainsprogect.data.dagger;

import com.geekbrains.geekbrainsprogect.ui.auth.view.AuthActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;

import dagger.Subcomponent;
@ProductScope
@Subcomponent(modules = {ProductModule.class, ProductTransactionModule.class})
public interface WarehouseComponent {
        void inject(ProductListActivity productListActivity);
        void inject(AuthActivity authActivity);
}
