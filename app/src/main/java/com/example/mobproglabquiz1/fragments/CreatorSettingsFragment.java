package com.example.mobproglabquiz1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mobproglabquiz1.LoginActivity;
import com.example.mobproglabquiz1.R;
import com.example.mobproglabquiz1.UserMainActivity;
import com.example.mobproglabquiz1.dao.CreatorDAO;

public class CreatorSettingsFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_creator_settings, container, false);

        LinearLayout userMode = view.findViewById(R.id.user_mode);
        userMode.setOnClickListener(button -> {
            Intent intent = new Intent(getActivity(), UserMainActivity.class);
            intent.putExtras(requireArguments());
            startActivity(intent);
            getActivity().finish();
        });

        LinearLayout logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(button -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }
}