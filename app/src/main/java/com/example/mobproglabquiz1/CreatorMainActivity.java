package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobproglabquiz1.fragments.CreatorOrderFragment;
import com.example.mobproglabquiz1.fragments.CreatorProfileFragment;
import com.example.mobproglabquiz1.fragments.CreatorSettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreatorMainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_main);

        bundle = getIntent().getExtras();

        bottomNavigationView = findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onSelect);

        loadFragment(CreatorProfileFragment.class);
    }

    private boolean onSelect(MenuItem item) {
        Class fragment = null;
        switch (item.getItemId()) {
            case R.id.profile_menu:
                fragment = CreatorProfileFragment.class;
                break;
            case R.id.order_menu:
                fragment = CreatorOrderFragment.class;
                break;
            case R.id.settings_menu:
                fragment = CreatorSettingsFragment.class;
                break;
        }
        return loadFragment(fragment);
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