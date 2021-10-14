package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        goToLoginPage();
    }

    private void registerPageForm(String firstName, String lastName, String email, String password) {
        Intent intent = new Intent();


    }
}