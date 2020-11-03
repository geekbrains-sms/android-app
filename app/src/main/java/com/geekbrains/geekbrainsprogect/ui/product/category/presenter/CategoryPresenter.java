package com.geekbrains.geekbrainsprogect.ui.product.category.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.CategoryInteractor;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryView;
import com.geekbrains.geekbrainsprogect.data.model.entity.Category;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class CategoryPresenter extends MvpPresenter<CategoryView> {
    CategoryInteractor categoryInteractor;

    public CategoryPresenter(CategoryInteractor categoryInteractor) {
        this.categoryInteractor = categoryInteractor;

        subscribeToCategoryChanges();
        getCategoryListFromServer();
    }

    private void getCategoryListFromServer() {
        Disposable disposable = categoryInteractor.saveFromServerToDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {}, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    private void subscribeToCategoryChanges() {
        Disposable disposable = categoryInteractor.getCategoryFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    getViewState().setDataToAdapter(categories);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }


    public void saveCategory(Category category) {
        Disposable disposable = categoryInteractor.addCategory(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    getViewState().showToast(R.string.category_create_sucesses);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }

    public void deleteCategory(Category category) {
        Disposable disposable = categoryInteractor.deleteCategory(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    getViewState().showToast(R.string.category_delete_sucesses);
                }, throwable -> {
                    getViewState().showAlertDialog(throwable.getMessage());
                });
    }
}
