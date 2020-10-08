package com.geekbrains.geekbrainsprogect.ui.product.category.presenter;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.AppData;
import com.geekbrains.geekbrainsprogect.ui.product.category.view.CategoryView;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class CategoryPresenter extends MvpPresenter<CategoryView> {

    public CategoryPresenter()
    {
        getCategoryList();
    }

    private void getCategoryList()
    {
        Single<Response<List<Category>>> single = AppData.getApiHelper().getCategoryList();
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categoryResponse ->{
            if(categoryResponse.isSuccessful())
            {
                List<Category>categories = categoryResponse.body();
                AppData.setCategoryList(categories);
                getViewState().setDataToAdapter(categories);
            }

        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }

    public void saveCategory(Category category) {
        Single<Response<Category>> single = AppData.getApiHelper().addCategory(category);
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categoryResponse ->{
            if(categoryResponse.isSuccessful())
            {
                AppData.getCategoryList().add(categoryResponse.body());
                getViewState().updateRecyclerView();
                getViewState().showToast(R.string.category_create_sucesses);
            }
            else
            {
                getViewState().showAlertDialog(categoryResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });

    }

    public void deleteCategory(Category category) {
        Single<Response<List<Category>>> single = AppData.getApiHelper().deleteCategoryById(category.getId());
        Disposable disposable = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categoryResponse ->{
            if(categoryResponse.isSuccessful())
            {
                AppData.setCategoryList(categoryResponse.body());
                getViewState().updateRecyclerView();
                getViewState().showToast(R.string.category_create_sucesses);
            }
            else
            {
                getViewState().showAlertDialog(categoryResponse.errorBody().string());
            }
        }, throwable -> {
            getViewState().showAlertDialog(throwable.getMessage());
        });
    }
}
