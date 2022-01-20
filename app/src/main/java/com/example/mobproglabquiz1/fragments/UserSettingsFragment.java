package com.example.mobproglabquiz1.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.mobproglabquiz1.CreatorMainActivity;
import com.example.mobproglabquiz1.CreatorRegisterActivity;
import com.example.mobproglabquiz1.LoginActivity;
import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.dao.CreatorDAO;
import com.google.gson.Gson;


public class UserSettingsFragment extends Fragment {

    View view;

    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        LinearLayout creatorMode = view.findViewById(R.id.creator_mode);
        creatorMode.setOnClickListener(button -> {
            progressDialog = new ProgressDialog(view.getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Logging in...");
            progressDialog.show();
            new CreatorDAO(view.getContext()).login(requireArguments().getInt("user_id"), onSuccessCallback, onErrorCallback);
        });

        LinearLayout logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(button -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        this.view = view;
        return view;
    }

    private Response.Listener<String> onSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("Response", response);
            ResultResponse result = new Gson().fromJson(response, ResultResponse.class);

            Bundle bundle = new Bundle();
            bundle.putInt("user_id", result.getUser_id());
            bundle.putInt("creator_id", result.getCreator_id());
            bundle.putString("name", requireArguments().getString("name"));

            Intent intent = new Intent(getActivity(), CreatorMainActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);
            progressDialog.dismiss();
            getActivity().finish();
        }
    };

    private Response.ErrorListener onErrorCallback = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error == null || error.networkResponse == null) {
                Toast.makeText(getActivity(), "Connection error.", Toast.LENGTH_LONG).show();
            }
            else {
                Bundle bundle = new Bundle();
                bundle.putInt("user_id", requireArguments().getInt("user_id"));
                Log.d("user_id", String.valueOf(requireArguments().getInt("user_id")));
                Intent intent = new Intent(getActivity(), CreatorRegisterActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1002);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1002) {
            Toast.makeText(getActivity(), "Successfully registered. You may now login as creator.", Toast.LENGTH_LONG).show();
        }
    }
}

class ResultResponse {
    private int user_id;
    private int creator_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }
}