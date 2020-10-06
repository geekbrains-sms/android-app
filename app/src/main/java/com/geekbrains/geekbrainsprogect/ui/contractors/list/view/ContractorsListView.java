package com.geekbrains.geekbrainsprogect.ui.contractors.list.view;

import com.geekbrains.geekbrainsprogect.data.Contractor;

import java.util.List;

import moxy.MvpView;

public interface ContractorsListView extends MvpView {
    void setDataToAdapter(List<Contractor> contractorList);
    void showToast(int tet);
    void showAlertDialog(String string);
    void updateRecyclerView();
}
