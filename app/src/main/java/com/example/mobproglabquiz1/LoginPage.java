package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        registerButton = findViewById(R.id.activity_login_page_register_button);
        loginButton = findViewById(R.id.activity_login_page_login_button);

        registerButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, RegisterPage.class);
            startActivity(intent);
        });
        loginButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        });
    }
}