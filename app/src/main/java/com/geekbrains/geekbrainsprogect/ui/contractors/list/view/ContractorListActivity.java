package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Contractor;
import com.geekbrains.geekbrainsprogect.ui.contractors.list.presenter.ContractorsListPresenter;
import com.geekbrains.geekbrainsprogect.ui.product.product_list.view.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class ContractorListActivity extends MvpAppCompatActivity implements ContractorsListView {
    @InjectPresenter
    ContractorsListPresenter presenter;
    @BindView(R.id.data_recycler)
    RecyclerView contractorsList;
    private ContractorsListAdapter adapter;
    private SearchView searchView;

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
        adapter.setOnCheckedClickListener(this::invalidateOptionsMenu);
    }

    private void showAddContractorDialog() {
        DialogFragment personalDialog = new ContractorDialog((old, contractor) -> {
            presenter.addContractor(contractor);
        });
        personalDialog.show(getSupportFragmentManager(), "contractorDialog");
    }

    private void recyclerSetting() {
        adapter = new ContractorsListAdapter();
        adapter.setOnItemClickListener(this::showEditContractorDialog);
        contractorsList.setAdapter(adapter);
        contractorsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contractorsList.addItemDecoration(new SimpleDividerItemDecoration(getApplication()));
    }


    public void showToast(int text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }


    public void setDataToAdapter(List<Contractor> body) {
        adapter.setAllContractor(body);
    }

    public void showAlertDialog(String string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(string);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
        });
        builder.create().show();
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }


    public void showEditContractorDialog(Contractor contractor) {
        DialogFragment personalDialog = new ContractorDialog(contractor, (contractorOld,contractorEdit) -> {
            presenter.editContractor(contractorEdit, contractorOld);
        });
        personalDialog.show(getSupportFragmentManager(), "contractorDialog");
    }

    public void createToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_list_menu, menu);
        if (adapter.getSelectedContractors() != null && adapter.getSelectedContractors().size() > 0) {
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

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) menu.findItem(R.id.bar_search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                for (Contractor contractor : adapter.getSelectedContractors()) {
                    if (contractor != null)
                        presenter.deleteContractor(contractor);
                }

        }
        return super.onOptionsItemSelected(item);
    }
}