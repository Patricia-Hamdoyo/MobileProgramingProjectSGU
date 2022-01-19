package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.example.mobproglabquiz1.dao.UserDAO;
import com.example.mobproglabquiz1.models.UserModel;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;

public class UserRegisterActivity extends AppCompatActivity {

    EditText fullName;
    EditText email;
    EditText password;
    Button registerButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        fullName = (EditText) findViewById(R.id.activity_register_fullname_input);
        email = (EditText) findViewById(R.id.activity_register_email_input);
        password = (EditText) findViewById(R.id.activity_register_password_input);

        registerButton = findViewById(R.id.activity_register_page_register_button);
        registerButton.setOnClickListener(view -> register(view));
    }

    private void register(View view) {
        if (isInputValid()) {
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating account...");
            progressDialog.show();
        }

        UserModel user = new UserModel(
                email.getText().toString(),
                fullName.getText().toString(),
                password.getText().toString()
        );
        Log.d("AuthInput", user.getEmail() + " " + user.getPassword());
        new UserDAO(view.getContext()).register(user, onLoginSuccessCallback, new VolleyErrorListener(this, progressDialog));
    }

    private Response.Listener<String> onLoginSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(UserRegisterActivity.class.getName(), response);
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
            progressDialog.dismiss();
        }
    };

    private boolean isInputValid() {
        boolean isAllValid = true;
        if (fullName.getText().length() == 0) {
            fullName.setError("Please fill in your full name!");
            isAllValid = false;
        }
        if (email.getText().length() == 0) {
            email.setError("Please fill in your email address!");
            isAllValid = false;
        }
        if (password.getText().length() <= 8) {
            password.setError("Passwords must be at least 8 characters!");
            isAllValid = false;
        }

        return isAllValid;
    }
}