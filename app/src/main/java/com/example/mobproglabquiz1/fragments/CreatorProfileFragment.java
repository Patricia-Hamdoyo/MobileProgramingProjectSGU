package com.example.mobproglabquiz1.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.mobproglabquiz1.JobCreateActivity;
import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.dao.CreatorDAO;
import com.example.mobproglabquiz1.models.JobModel;
import com.example.mobproglabquiz1.recycler.JobModelAdapter;
import com.example.mobproglabquiz1.utils.VolleyErrorListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CreatorProfileFragment extends Fragment {

    View view;
    TextView windowTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_creator_profile, container, false);
        windowTitle = view.findViewById(R.id.window_title);
        windowTitle.setText("Welcome back, " + requireArguments().getString("name"));

        ImageView addBtn = view.findViewById(R.id.add_jobs);
        addBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), JobCreateActivity.class);
            intent.putExtras(requireArguments());

            startActivityForResult(intent, 1003);
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new CreatorDAO(getActivity()).getJobs(requireArguments().getInt("creator_id"), onSuccessCallback, new VolleyErrorListener(getActivity(), null));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1003 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "Successfully created a new job!", Toast.LENGTH_LONG).show();
            getFragmentManager().beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
    }

    private Response.Listener<String> onSuccessCallback = new Response.Listener<String>() {
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