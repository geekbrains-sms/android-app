package com.geekbrains.geekbrainsprogect.ui.auth.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.geekbrains.geekbrainsprogect.MainNavigateActivity;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;
import com.google.android.material.textfield.TextInputEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class AuthActivity extends MvpAppCompatActivity implements AuthView{
    @InjectPresenter
    AuthPresenter authPresenter;
    @BindView(R.id.login_edit_text)
    TextInputEditText editLogin;
    @BindView(R.id.password_edit_text)
    TextInputEditText editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.sign_in_button})
    void onClickButton()
    {
        authPresenter.signIn(editLogin.getText().toString(), editPassword.getText().toString());
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainNavigateActivity.class);
        startActivity(intent);
    }
}