package com.geekbrains.geekbrainsprogect.data.dagger;

import com.geekbrains.geekbrainsprogect.data.api.ApiHelper;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(AuthPresenter authPresenter);
    void inject(AppData appData);
    void inject(ApiHelper apiHelper);
    void inject(ProductListActivity productListActivity);
    void inject(CategoryActivity categoryActivity);
}
