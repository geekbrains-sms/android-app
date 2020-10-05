package com.geekbrains.geekbrainsprogect.ui.personal.personal_list.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.User;
import com.google.android.material.textfield.TextInputEditText;

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
    


    OnClickEditButton onClickEditButton;
    private User user;

    public PersonalDialog(OnClickEditButton onClickEditButton)
    {
        this.onClickEditButton = onClickEditButton;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.edit_personal_dialog, null);
        ButterKnife.bind(this, view);


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    private void setDataToViews()
    {
        loginEdit.setText(user.getLogin());
        firstNameEdit.setText(user.getFirstname());
        lastNameEdit.setText(user.getLastname());

    }

    @OnClick(R.id.edit_personal_button)
    void onClick()
    {
        String login = loginEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = passwordConfirmEdit.getText().toString();
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String
        User user = new User(login, )
        onClickEditButton.onClick(user);
    }


    public interface OnClickEditButton
    {
        void onClick(User user);
    }


}
