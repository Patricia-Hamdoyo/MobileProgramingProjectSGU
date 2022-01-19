package com.example.mobproglabquiz1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.mobproglabquiz1.dao.UserDAO;
import com.example.mobproglabquiz1.models.UserModel;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    Button loginButton;
    Button registerButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.activity_login_email_input);
        password = findViewById(R.id.activity_login_password_input);

        registerButton = findViewById(R.id.activity_login_page_register_button);
        loginButton = findViewById(R.id.activity_login_page_login_button);

        registerButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, UserRegisterActivity.class);
            startActivityForResult(intent, 1001);
        });
        loginButton.setOnClickListener((view) -> {
            login(view);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Successfully registered.", Toast.LENGTH_LONG).show();
        }
    }

    private void login(View view) {
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        UserModel user = new UserModel(
                email.getText().toString(),
                password.getText().toString()
        );
        Log.d("AuthInput", user.getEmail() + " " + user.getPassword());
        new UserDAO(view.getContext()).login(user, onLoginSuccessCallback, new VolleyErrorListener(this, progressDialog));
    }

    private Response.Listener<String> onLoginSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(LoginActivity.class.getName(), response);
            ResultResponse result = new Gson().fromJson(response, ResultResponse.class);
            Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("user_id", result.getUser_id());
            bundle.putString("name", result.getName());
            intent.putExtras(bundle);

            progressDialog.dismiss();
            startActivity(intent);
            finish();
        }
    };
}

class ResultResponse {
    int user_id;
    String name;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}