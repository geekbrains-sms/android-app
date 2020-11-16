package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.model.entity.Role;
import com.geekbrains.geekbrainsprogect.data.model.entity.User;
import com.geekbrains.geekbrainsprogect.domain.model.UserModel;
import com.geekbrains.geekbrainsprogect.ui.base.BaseDialog;
import com.geekbrains.geekbrainsprogect.ui.base.Item;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDialog extends BaseDialog implements View.OnClickListener {

    @BindView(R.id.login_edit_text)
    TextInputEditText loginEdit;
    @BindView(R.id.firstname_edit_text)
    TextInputEditText firstNameEdit;
    @BindView(R.id.lastname_edit_text)
    TextInputEditText lastNameEdit;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEdit;
    @BindView(R.id.password_confirm_edit_text)
    TextInputEditText passwordConfirmEdit;
    @BindView(R.id.email_edit_text)
    TextInputEditText emailEdit;
    @BindView(R.id.phone_edit_text)
    TextInputEditText phoneEdit;
    @BindView(R.id.select_roles)
    AutoCompleteTextView rolesSelect;
    @BindView(R.id.role_container)
    LinearLayout mainContainer;


    private UserModel user;
    private ArrayAdapter<Role>adapter;

    private List<Role>allRoles;
    private List<Role>selectedRoles;
    


    OnClickEditButton onClickEditButton;


    public PersonalDialog(UserModel user, OnClickEditButton onClickEditButton, List<Role> allRoles)
    {
        this.allRoles = allRoles;
        this.onClickEditButton = onClickEditButton;
        this.user = user;
        selectedRoles = new ArrayList<>();
    }
    public PersonalDialog(OnClickEditButton onClickEditButton, List<Role> allRoles)
    {
        this.onClickEditButton = onClickEditButton;
        this.allRoles = allRoles;
        selectedRoles = new ArrayList<>();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.edit_personal_dialog, null);
        ButterKnife.bind(this, view);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addPositiveButtonListener();
        setAdapter();
        if(user != null)
        {
            setDataToViews();
        }
    }

    @Override
    protected <T extends Item> void addItemToSelectedList(Class<T> type, TextView textView, T item) {
        if(type == Role.class && item != null)
        {
            Role role =  (Role)item;
            selectedRoles.add(role);
            textView.setText(role.getTitle());
        }
    }


    private void setDataToViews()
    {
        loginEdit.setText(user.getLogin());
        firstNameEdit.setText(user.getFirstName());
        lastNameEdit.setText(user.getLastname());

        for(Role role : user.getRoles())
        {
            addDataItem(Role.class, role, mainContainer);
            selectedRoles.add(role);
        }
        updateAdapter(adapter, allRoles, selectedRoles);

    }


    private void setAdapter() {
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, getActualItemList(allRoles,selectedRoles));
        rolesSelect.setAdapter(adapter);
    }


    @Override
    protected void mappedItem(DialogInterface dialogInterface) {
        String login = loginEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = passwordConfirmEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String phone = phoneEdit.getText().toString();
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();

        if(password.equals(confirmPassword) && !TextUtils.isEmpty(login) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !selectedRoles.isEmpty())
        {
            UserModel userLocal = new UserModel(0,login, firstName, lastName, email, phone, selectedRoles);
            userLocal.setPassword(password);
            if(user != null)
            {
                userLocal.setId(user.getId());
            }
            onClickEditButton.onClick(userLocal);
            dialogInterface.dismiss();
        }
    }


    @OnClick(R.id.add_role_button)
    void onClickAddButton()
    {
        addItemToContainer(Role.class, rolesSelect, selectedRoles, allRoles, mainContainer, adapter);
    }

    @Override
    public void onClick(View v) {
        removeItem(v, mainContainer, selectedRoles, allRoles, rolesSelect, adapter);
    }

    public interface OnClickEditButton
    {
        void onClick(UserModel newUser);
    }
}
