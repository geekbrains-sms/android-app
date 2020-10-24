package com.geekbrains.geekbrainsprogect.ui.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.geekbrains.geekbrainsprogect.MainNavigateActivity;
import com.geekbrains.geekbrainsprogect.R;
import com.geekbrains.geekbrainsprogect.data.api.AuthenticationInterceptor;
import com.geekbrains.geekbrainsprogect.data.dagger.application.AppData;
import com.geekbrains.geekbrainsprogect.ui.auth.model.AuthRepository;
import com.geekbrains.geekbrainsprogect.ui.base.BaseActivity;
import com.geekbrains.geekbrainsprogect.ui.auth.presenter.AuthPresenter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import okhttp3.OkHttpClient;

public class AuthActivity extends BaseActivity implements AuthView{
    private static final int MIN_TEXT_LENGTH = 4;
    @InjectPresenter
    AuthPresenter authPresenter;
    @BindView(R.id.login_edit_text)
    TextInputEditText editLogin;
    @BindView(R.id.firstname_edit_text)
    TextInputEditText editPassword;
    @BindView(R.id.auth_progress_bar)
    ProgressBar progressBar;
    @Inject
    AuthRepository authRepository;
    @Inject
    AuthenticationInterceptor interceptor;

    @ProvidePresenter
    AuthPresenter provideAuthPresenter()
    {
        AppData.getComponentsManager().getWarehouseComponent().inject(this);
        return new AuthPresenter(authRepository, interceptor);
    }



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

    public void showProgressBar(boolean show)
    {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoginError(int text) {

    }

    @Override
    public void showPasswordError(int text) {

    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainNavigateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(progressBar != null)
        {
            progressBar.setVisibility(View.GONE);
        }
    }
}