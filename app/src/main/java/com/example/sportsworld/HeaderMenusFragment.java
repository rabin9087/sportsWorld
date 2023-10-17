package com.example.sportsworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HeaderMenusFragment extends Fragment {

    TextView FirstName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_header_menus, container, false);

        FirstName = view.findViewById(R.id.FirstName);

        return view;
    }
}