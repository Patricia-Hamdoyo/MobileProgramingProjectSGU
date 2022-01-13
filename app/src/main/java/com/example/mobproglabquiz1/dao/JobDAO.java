package com.example.mobproglabquiz1.dao;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobproglabquiz1.models.JobModel;
import com.example.mobproglabquiz1.utils.Constants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JobDAO extends DAO<JobModel> {

    private Context context;

    public JobDAO(Context context) {
        super(context);
    }

    @Override
    public void get(JobModel model, Response.Listener<String> successAction, Response.ErrorListener errorAction) {

    }

    @Override
    public void getAll(int refId, Response.Listener<String> successAction, Response.ErrorListener errorAction) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Constants.getRoute("jobs"),
                successAction,
                errorAction
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json; charset=utf-8");
                return header;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(request);
    }

    @Override
    public void post(JobModel model, Response.Listener<String> successAction, Response.ErrorListener errorAction) {

    }

    @Override
    public void update(JobModel model, Response.Listener<String> successAction, Response.ErrorListener errorAction) {

    }

    @Override
    public void delete(int id, Response.Listener<String> successAction, Response.ErrorListener errorAction) {

    }
}
