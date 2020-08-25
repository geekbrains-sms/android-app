package com.geekbrains.geekbrainsprogect;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavigateActivity extends AppCompatActivity {
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    public static String TAG = "MainNavigateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        navViewSetting();
    }

    private void navViewSetting() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_goods, R.id.navigation_warehouse, R.id.navigation_personal)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
//        Fragment selectedFragment = null;
//        Log.d(TAG, "item: " + item.getItemId());
//        switch (item.getItemId())
//        {
//            case R.id.navigation_goods:
//                selectedFragment = new ProductFragment();
//                break;
//            case R.id.navigation_warehouse:
//                selectedFragment = new WarehouseFragment();
//                break;
//            case R.id.navigation_personal:
//                selectedFragment = new PersonalFragment();
//                break;
//        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
//        return true;
//    };

}