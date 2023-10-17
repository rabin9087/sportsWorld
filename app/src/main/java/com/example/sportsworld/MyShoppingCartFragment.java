package com.example.sportsworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyShoppingCartFragment extends Fragment {

Button checkOutButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_shopping_cart, container, false);

        checkOutButton = view.findViewById(R.id.checkOutButton);

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Your checkOut has been done", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}