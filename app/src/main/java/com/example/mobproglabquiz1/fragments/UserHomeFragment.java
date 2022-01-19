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
import com.example.mobproglabquiz1.utils.VolleyErrorListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserHomeFragment extends Fragment {

    View view;
    ArrayList<JobModel> jobs;
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
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    JobModel job = new JobModel(
                            jsonObj.getInt("id"),
                            jsonObj.getString("title"),
                            jsonObj.getString("description"),
                            jsonObj.getDouble("price"),
                            jsonObj.getString("creator")
                    );
                    jobs.add(job);
                }

                RecyclerView rv = view.findViewById(R.id.recycler_view);

                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                JobModelAdapter dataModelAdapter = new JobModelAdapter(jobs);

                rv.setAdapter(dataModelAdapter);
                rv.setLayoutManager(layoutManager);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


}

class JobModelAdapter extends RecyclerView.Adapter<JobModelViewHolder> {
    ArrayList<JobModel> jobs;
    public JobModelAdapter(ArrayList<JobModel> jobs) {
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public JobModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_card_jobs, parent, false);

        return new JobModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobModelViewHolder holder, int position) {
        JobModel job = jobs.get(position);
        holder.title.setText(job.getTitle());
        holder.creator.setText(job.getCreator());
        holder.price.setText(Double.toString(job.getPrice()));
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}

class JobModelViewHolder extends RecyclerView.ViewHolder {
    View cardView;
    TextView title;
    TextView creator;
    TextView price;

    public JobModelViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title_text);
        creator = itemView.findViewById(R.id.creator_text);
        price = itemView.findViewById(R.id.price_text);

    }
}