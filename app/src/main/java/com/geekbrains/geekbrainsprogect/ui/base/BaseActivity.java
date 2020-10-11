package com.geekbrains.geekbrainsprogect.ui.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.geekbrains.geekbrainsprogect.R;

import moxy.MvpAppCompatActivity;

public class BaseActivity extends MvpAppCompatActivity implements BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolbar();
    }

    @Override
    public void showToast(int text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void createToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public void showAlertDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error);
        builder.setMessage(text);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {});
        builder.create().show();
    }
}
