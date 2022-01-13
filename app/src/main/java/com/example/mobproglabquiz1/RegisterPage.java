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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobproglabquiz1.dao.UserDAO;
import com.example.mobproglabquiz1.models.UserModel;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {

    EditText fullName;
    EditText email;
    EditText password;
    Button registerButton;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fullName = (EditText) findViewById(R.id.activity_register_fullname_input);
        email = (EditText) findViewById(R.id.activity_register_email_input);
        password = (EditText) findViewById(R.id.activity_register_email_input);

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
        new UserDAO(view.getContext()).register(user, onLoginSuccessCallback, new VolleyErrorListener(this, progressDialog));
    }

    private Response.Listener<String> onLoginSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(RegisterPage.class.getName(), response);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("email", email.getText().toString());
            bundle.putString("password", password.getText().toString());
            intent.putExtras(bundle);
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