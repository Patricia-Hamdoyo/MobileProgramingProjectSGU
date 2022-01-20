package com.example.mobproglabquiz1.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.models.JobModel;

import java.util.ArrayList;

public class JobModelAdapter extends RecyclerView.Adapter<JobModelViewHolder> {

    ArrayList<JobModel> jobs;
    View view;
    public JobModelAdapter(ArrayList<JobModel> jobs) {
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public JobModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_card_jobs, parent, false);

        return new JobModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobModelViewHolder holder, int position) {
        JobModel job = jobs.get(position);
        holder.title.setText(job.getTitle());
        holder.creator.setText(job.getFullname());
        holder.price.setText("Rp." + job.getPrice());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
