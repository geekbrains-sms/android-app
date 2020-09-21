package com.geekbrains.geekbrainsprogect.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEdit;
    @BindView(R.id.fullname_edit_text)
    TextInputEditText fullnameEdit;
    @BindView(R.id.edit_personal_button)
    Button button;

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
        View view = getLayoutInflater().inflate(R.layout.activity_personal_list, null);
        ButterKnife.bind(view);


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @OnClick(R.id.edit_personal_button)
    void onClick()
    {
        String login = loginEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String fullname = fullnameEdit.getText().toString();
        onClickEditButton.onClick(login, password, fullname);
    }


    public interface OnClickEditButton
    {
        void onClick(String login, String password, String fullname);
    }


}
