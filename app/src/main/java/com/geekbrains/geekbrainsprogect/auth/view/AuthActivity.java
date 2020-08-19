package com.geekbrains.geekbrainsprogect.auth.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.auth.presenter.AuthPresenter;
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

    @OnClick({R.id.registration_button, R.id.sign_in_button})
    void onClickButton(View view)
    {
        if(view.getId() == R.id.registration_button)
        {
            String loginText = editLogin.getText().toString();
            String passwordText = editPassword.getText().toString();

            authPresenter.registerNewUser(loginText, passwordText);
        }
        else if(view.getId() == R.id.sign_in_button)
        {
            authPresenter.getAllUser();
        }
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }
}