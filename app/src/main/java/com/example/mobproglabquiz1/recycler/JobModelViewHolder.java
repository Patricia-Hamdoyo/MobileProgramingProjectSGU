package com.example.mobproglabquiz1.recycler;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.models.JobModel;

import java.util.ArrayList;


public class JobModelViewHolder extends RecyclerView.ViewHolder {
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
