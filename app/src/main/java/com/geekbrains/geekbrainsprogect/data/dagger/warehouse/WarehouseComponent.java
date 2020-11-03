package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import com.geekbrains.geekbrainsprogect.data.model.entity.Category;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;
import com.geekbrains.geekbrainsprogect.ui.auth.view.AuthActivity;
import com.geekbrains.geekbrainsprogect.ui.base.BaseActivity;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.view.ContractorListActivity;
import com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view.PersonalListActivity;
import com.geekbrains.geekbrainsprogect.ui.product.actions.presenter.ActionProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.actions.view.ActionProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryActivity;
import com.geekbrains.geekbrainsprogect.ui.product.detail.view.DetailProductActivity;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.ProductListActivity;

import dagger.Subcomponent;
@ProductScope
@Subcomponent(modules = {InteractorModule.class, RepositoryModule.class, MapperModule.class})
public interface WarehouseComponent {
        void inject(ProductListActivity productListActivity);
        void inject(AuthActivity authActivity);
        void inject(CategoryActivity categoryActivity);
        void inject(DetailProductActivity detailProductActivity);
        void inject(ActionProductActivity actionProductActivity);
        void inject(ContractorListActivity contractorListActivity);
        void inject(PersonalListActivity personalListActivity);
}
