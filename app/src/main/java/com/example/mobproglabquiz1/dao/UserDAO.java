package com.example.mobproglabquiz1.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobproglabquiz1.models.UserModel;
import com.example.mobproglabquiz1.utils.Constants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    private Context context;

    public UserDAO(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void register(final UserModel model, Response.Listener<String> successAction, Response.ErrorListener errorAction) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.getRoute("user/register"),
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

            @Override
            public byte[] getBody() throws AuthFailureError {
                Log.d("JSON", new Gson().toJson(model));
                return new Gson().toJson(model).getBytes();
            }
        };
        queue.add(request);
    }

    public void login(final UserModel model, Response.Listener<String> successAction, Response.ErrorListener errorAction) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.getRoute("user/login"),
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

            @Override
            public byte[] getBody() throws AuthFailureError {
                Log.d("JSON", new Gson().toJson(model));
                return new Gson().toJson(model).getBytes();
            }
        };
        queue.add(request);
    }
}
