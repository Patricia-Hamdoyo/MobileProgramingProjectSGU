package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.example.mobproglabquiz1.dao.CreatorDAO;
import com.example.mobproglabquiz1.models.CreatorModel;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;

public class CreatorRegisterActivity extends AppCompatActivity {

    Bundle bundle;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator_register);

        bundle = getIntent().getExtras();

        registerBtn = findViewById(R.id.register);
        registerBtn.setOnClickListener(this::register);
    }

    private void register(View view) {
        EditText biodata = findViewById(R.id.biodata);
        EditText education = findViewById(R.id.education);
        EditText experience = findViewById(R.id.experience);
        Log.d("user_id", String.valueOf(bundle.getInt("user_id")));


        CreatorModel creator = new CreatorModel(
                bundle.getInt("user_id"),
                biodata.getText().toString(),
                education.getText().toString(),
                experience.getText().toString()
        );

        new CreatorDAO(this).register(creator, onSuccessAction, new VolleyErrorListener(this, null));
    }

    private Response.Listener<String> onSuccessAction = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d(CreatorRegisterActivity.class.getName(), response);
            setResult(RESULT_OK);
            finish();
        }
    };
}