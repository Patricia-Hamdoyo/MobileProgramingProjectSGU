package com.example.mobproglabquiz1.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class VolleyErrorListener implements Response.ErrorListener {

    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_UNAUTHORIZED = 401; // incorrect password
    private static final int HTTP_FORBIDDEN = 403; // no token
    private static final int HTTP_NOT_FOUND = 404;
    private static final int HTTP_CONFLICT = 409; // signup duplicate entry

    private Context context;
    private ProgressDialog progressDialog;

    public VolleyErrorListener(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(VolleyErrorListener.class.getName(), "Error caught");
        try {
            Log.e(VolleyErrorListener.class.getName(), new String(error.networkResponse.data, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (error.networkResponse == null) {
            Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show();
            if (progressDialog != null)
                progressDialog.dismiss();
            return;
        }
        ErrorResponseWrapper errorResponse = new Gson().fromJson(error.getMessage(), ErrorResponseWrapper.class);
        if (errorResponse.getError() != null) {
            Toast.makeText(context, errorResponse.getError(), Toast.LENGTH_LONG).show();
        } else {
            Log.d("notice", "Hola");

        }
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}


