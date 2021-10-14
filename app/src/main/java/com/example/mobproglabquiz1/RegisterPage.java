package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerButton = findViewById(R.id.activity_register_page_register_button);
        registerButton.setOnClickListener((view) -> {
            Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG);
            finish();
        });
    }

}