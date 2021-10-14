package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

public class LoginPage extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
//    private ActivityListPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

//        binding = ActivityListPageBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
    }
}