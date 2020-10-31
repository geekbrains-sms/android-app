package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.data.model.entity.Contractor;
import com.geekbrains.geekbrainsprogect.domain.interactor.contract.ContractorInteractor;
import com.geekbrains.geekbrainsprogect.ui.base.BaseListAdapter;
import com.geekbrains.geekbrainsprogect.ui.base.ListActivity;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.presenter.ContractorsListPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ContractorListActivity extends ListActivity implements ContractorsListView {
    @InjectPresenter
    ContractorsListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView contractorsList;
    private ContractorsListAdapter adapter;
    @Inject
    ContractorInteractor contractorInteractor;
    @ProvidePresenter
    ContractorsListPresenter provideContractorListPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new ContractorsListPresenter(contractorInteractor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        ButterKnife.bind(this);
        recyclerSetting();
        setListeners();
        createToolbar();
    }

    @OnClick({R.id.add_product_float_action})
    void onClick(View view) {
        showAddContractorDialog();
    }


    private void setListeners() {
        adapter.setOnItemClickListener(new BaseListAdapter.IOnItemClickListener<Contractor>() {
            @Override
            public void onItemClick(Contractor item) {
                showEditContractorDialog(item);
            }

            @Override
            public void onItemChangeChecked() {
                invalidateOptionsMenu();
            }
        });
    }

    private void showAddContractorDialog() {
        DialogFragment personalDialog = new ContractorDialog(contractor -> {
            presenter.addContractor(contractor);
        });
        personalDialog.show(getSupportFragmentManager(), "contractorDialog");
    }

    private void recyclerSetting() {
        adapter = new ContractorsListAdapter(getApplicationContext());
        contractorsList.setAdapter(adapter);
        contractorsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contractorsList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
        setAdapter(adapter);
    }

    public void setDataToAdapter(List<Contractor> body) {
        adapter.setItemList(body);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }


    public void showEditContractorDialog(Contractor contractor) {
        DialogFragment personalDialog = new ContractorDialog(contractor, contractorEdit -> {
            presenter.editContractor(contractorEdit);
        });
        personalDialog.show(getSupportFragmentManager(), "contractorDialog");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedList() != null && adapter.getSelectedList().size() > 0) {
            menu.findItem(R.id.bar_search).setVisible(false);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(true);
            menu.findItem(R.id.filter).setVisible(false);
        } else {
            menu.findItem(R.id.bar_search).setVisible(true);
            menu.findItem(R.id.open).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.filter).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void delete() {
        for (Contractor contractor : adapter.getSelectedList()) {
            if (contractor != null)
                presenter.deleteContractor(contractor);
        }
    }
    @Override
    protected void open() {}

    @Override
    protected void filter() {}
}