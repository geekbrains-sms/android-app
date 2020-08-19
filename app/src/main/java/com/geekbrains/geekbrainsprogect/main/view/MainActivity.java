package com.geekbrains.geekbrainsprogect.main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.geekbrains.geekbrainsprogect.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;

public class MainActivity extends MvpAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}