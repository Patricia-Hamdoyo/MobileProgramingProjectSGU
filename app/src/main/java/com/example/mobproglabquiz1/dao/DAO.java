package com.example.mobproglabquiz1.dao;

import android.content.Context;

import com.android.volley.Response;

public abstract class DAO<T> {

    private Context context;

    public DAO(Context context) {
        this.context = context;
    }

    public Context getContext() { return this.context; }

    public abstract void get(T model, Response.Listener<String> successAction, Response.ErrorListener errorAction);
    public abstract void getAll(int refId, Response.Listener<String> successAction, Response.ErrorListener errorAction);
    public abstract void post(T model, Response.Listener<String> successAction, Response.ErrorListener errorAction);
    public abstract void update(T model, Response.Listener<String> successAction, Response.ErrorListener errorAction);
    public abstract void delete(int id, Response.Listener<String> successAction, Response.ErrorListener errorAction);

}
