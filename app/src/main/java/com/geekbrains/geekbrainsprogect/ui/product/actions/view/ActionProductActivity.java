package com.geekbrains.geekbrainsprogect.ui.product.actions.view;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;


import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.UserActionInteractor;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.data.model.entity.UserAction;
import com.geekbrains.geekbrainsprogect.ui.product.actions.presenter.ActionProductPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ActionProductActivity extends ListActivity implements ActionProductView {
    @InjectPresenter
    ActionProductPresenter presenter;
    ActionProductAdapter adapter;
    @BindView(R.id.add_product_float_action)
    FloatingActionButton addButton;
    @BindView(R.id.data_recycler)
    RecyclerView dataList;
    @Inject
    UserActionInteractor userActionInteractor;
    @ProvidePresenter
    ActionProductPresenter provideProductPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new ActionProductPresenter(userActionInteractor);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        ButterKnife.bind(this);
        addButton.setVisibility(View.GONE);
        createRecycler();
    }

    private void createRecycler() {
        adapter = new ActionProductAdapter(getApplicationContext());
        dataList.setAdapter(adapter);
        setAdapter(adapter);
        dataList.setLayoutManager(new LinearLayoutManager(getApplication()));
        dataList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }


    @Override
    public void setDataToAdapter(List<UserAction> body) {
        adapter.setItemList(body);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);

            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void deleteElement() {}

    @Override
    protected void delete() {}

    @Override
    protected void open() {}

    @Override
    protected void filter() {}
}