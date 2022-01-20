package com.example.mobproglabquiz1.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.dao.JobDAO;
import com.example.mobproglabquiz1.models.JobModel;
import com.example.mobproglabquiz1.recycler.JobModelAdapter;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class UserHomeFragment extends Fragment {

    View view;
    TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        title = view.findViewById(R.id.window_title);
        this.view = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String name = "Hello, " + requireArguments().getString("name");
        title.setText(name);

        new JobDAO(view.getContext()).getAll(0, onLoginSuccessCallback, new VolleyErrorListener(view.getContext(), null));
    }

    private Response.Listener<String> onLoginSuccessCallback = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            JobModel[] jobs = new Gson().fromJson(response, JobModel[].class);

            RecyclerView rv = view.findViewById(R.id.recycler_view);

            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            JobModelAdapter jobModelAdapter = new JobModelAdapter(new ArrayList<>(Arrays.asList(jobs)));

            rv.setAdapter(jobModelAdapter);
            rv.setLayoutManager(layoutManager);

        }
    };


}