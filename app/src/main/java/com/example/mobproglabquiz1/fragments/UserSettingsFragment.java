package com.example.mobproglabquiz1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.dao.CreatorDAO;


public class UserSettingsFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        LinearLayout creatorMode = view.findViewById(R.id.creator_mode);
        creatorMode.setOnClickListener(button -> {
            new CreatorDAO(view.getContext()).login(requireArguments().getInt("user_id"), onSuccessCallback, onErrorCallback);
        });

        this.view = view;
        return view;
    }

    private Response.Listener<String> onSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("Response", response);
        }
    };

    private Response.ErrorListener onErrorCallback = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error == null || error.networkResponse == null) {
                Toast.makeText(view.getContext(), "Connection error.", Toast.LENGTH_LONG).show();
            }
        }
    };
}