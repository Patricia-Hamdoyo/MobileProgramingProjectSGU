package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mobproglabquiz1.fragments.HomeFragment;
import com.example.mobproglabquiz1.fragments.OrderFragment;
import com.example.mobproglabquiz1.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bundle = getIntent().getExtras();

        bottomNavigationView = findViewById(R.id.homepage_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Class fragment = null;
            
            switch (item.getItemId()){
                case R.id.home_menu:
                    fragment = HomeFragment.class;
                    break;
                case R.id.order_menu:
                    fragment = OrderFragment.class;
                    break;
                case R.id.settings_menu:
                    fragment = SettingsFragment.class;
                    break;
            }
            return loadFragment(fragment);
        });

        loadFragment(HomeFragment.class);
    }

    private boolean loadFragment(Class fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, bundle)
                    .commit();
            return true;
        }
        return false;
    }
}