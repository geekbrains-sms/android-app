package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.role.RoleManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.Role;
import com.geekbrains.geekbrainsprogect.data.User;
import com.geekbrains.geekbrainsprogect.ui.product.model.Category;
import com.geekbrains.geekbrainsprogect.ui.product.model.Product;
import com.geekbrains.geekbrainsprogect.ui.product.model.Unit;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDialog extends DialogFragment {

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

    private Role selectedRole;
    private List<Role>roles;
    private User user;
    


    OnClickEditButton onClickEditButton;


    public PersonalDialog(User user, OnClickEditButton onClickEditButton, List<Role>roles)
    {
        this.onClickEditButton = onClickEditButton;
        this.roles = roles;
        this.user = user;
    }
    public PersonalDialog(OnClickEditButton onClickEditButton, List<Role>roles)
    {
        this.onClickEditButton = onClickEditButton;
        this.roles = roles;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.edit_personal_dialog, null);
        ButterKnife.bind(this, view);
        if(user != null)
        {
            setDataToViews();
        }
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setClickListener();
    }

    private void setClickListener() {
        AlertDialog dialog = (AlertDialog) getDialog();
        assert dialog != null;
        dialog.setOnShowListener(dialog1 -> {
            Button button = ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE);

            button.setOnClickListener(v -> {
                String login = loginEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                String confirmPassword = passwordConfirmEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String phone = phoneEdit.getText().toString();
                String firstName = firstNameEdit.getText().toString();
                String lastName = lastNameEdit.getText().toString();

                if(password.equals(confirmPassword) && textEntered(login) && textEntered(password) && textEntered(firstName) && textEntered(lastName) && selectedRole != null)
                {
                    List<Role>roles = new ArrayList<>();
                    roles.add(selectedRole);
                    User userLocal = new User(login, firstName, lastName, email, phone, password, roles);

                    if(user != null)
                    {
                        userLocal.setId(user.getId());
                    }
                    onClickEditButton.onClick(userLocal);
                    dialog1.dismiss();
                }
            });
        });

        ArrayAdapter<Role> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, roles);
        rolesSelect.setAdapter(adapter);
        rolesSelect.setOnItemClickListener((parent, view, position, id) -> selectedRole = roles.get(position));
    }
    private boolean textEntered(String text) {
        if(text != null && !text.trim().equals(""))
        {
            return true;
        }
        return false;
    }

    private void setDataToViews()
    {
        loginEdit.setText(user.getLogin());
        firstNameEdit.setText(user.getFirstname());
        lastNameEdit.setText(user.getLastname());

        StringBuilder builder = new StringBuilder();
        for(Role role : user.getRoles())
        {
            builder.append(role.getName()).append(" ");
        }
        rolesSelect.setText(builder.toString());
    }

    public interface OnClickEditButton
    {
        void onClick(User user);
    }


}
