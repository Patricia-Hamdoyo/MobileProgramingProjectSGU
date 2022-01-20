package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.example.mobproglabquiz1.dao.JobDAO;
import com.example.mobproglabquiz1.models.JobModel;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;

public class JobCreateActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText price;

    Bundle bundle;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_create);

        title = findViewById(R.id.job_title);
        description = findViewById(R.id.job_desc);
        price = findViewById(R.id.job_price);

        bundle = getIntent().getExtras();

        Button createBtn = findViewById(R.id.create_job);
        createBtn.setOnClickListener(this::create);
    }

    private void create(View view) {
        if (validateInput()) {
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating job...");
            progressDialog.show();

            JobModel job = new JobModel(
                    title.getText().toString(),
                    description.getText().toString(),
                    Double.parseDouble(price.getText().toString()),
                    bundle.getInt("creator_id")
            );
            new JobDAO(this).post(job, onSuccessCallback, new VolleyErrorListener(this, progressDialog));
        }
    }
    private Response.Listener<String> onSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            setResult(Activity.RESULT_OK);
            progressDialog.dismiss();
            finish();
        }
    };

    private boolean validateInput() {
        boolean status = true;
        if (title.getText().length() == 0) {
            title.setError("Title must not be empty!");
            status = false;
        }
        if (description.getText().length() == 0) {
            description.setError("Description must not be empty!");
            status = false;
        }
        if (price.getText().length() == 0) {
            price.setError("Price must not be empty!");
            status = false;
        }
        return status;
    }
}