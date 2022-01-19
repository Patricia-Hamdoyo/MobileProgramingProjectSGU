package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobproglabquiz1.fragments.UserHomeFragment;
import com.example.mobproglabquiz1.fragments.UserOrderFragment;
import com.example.mobproglabquiz1.fragments.UserSettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        bundle = getIntent().getExtras();

        bottomNavigationView = findViewById(R.id.homepage_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Class fragment = null;
            
            switch (item.getItemId()){
                case R.id.home_menu:
                    fragment = UserHomeFragment.class;
                    break;
                case R.id.order_menu:
                    fragment = UserOrderFragment.class;
                    break;
                case R.id.settings_menu:
                    fragment = UserSettingsFragment.class;
                    break;
            }
            return loadFragment(fragment);
        });

        loadFragment(UserHomeFragment.class);
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