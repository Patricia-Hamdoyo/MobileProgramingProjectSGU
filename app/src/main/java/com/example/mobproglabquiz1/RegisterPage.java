package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerPageForm();
    }

    private void registerPageForm(String firstName, String lastName, String email, Password password) {

    }
}