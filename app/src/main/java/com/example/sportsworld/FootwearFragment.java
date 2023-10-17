package com.example.sportsworld;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FootwearFragment extends Fragment {

    TextView usernameValueFootwear;

    ArrayList<String> itemName;
    ArrayList<String> description;
    ArrayList<String> price;
    ArrayList<String> brand;

    ImageButton createAccommodationButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_footwear,container, false);

        usernameValueFootwear = view.findViewById(R.id.usernameValueFootwear);
        assert this.getArguments() != null;
        usernameValueFootwear.setText(this.getArguments().getString("USER"));


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFootwear);

        itemName = new ArrayList<>();
        description = new ArrayList<>();
        price = new ArrayList<>();
        brand= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectFootwearQuery();

        recyclerView.setAdapter(new RecyclerAdapter(itemName, description, price, brand));

//        createAccommodationButton =view.findViewById(R.id.createAccommodationButton);
//        createAccommodationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                String userName = usernameValueAccommodation.getText().toString();
//                bundle.putString("createUserNameAccommodation", userName);
//                PostNewAccommodationFragment postNewAccommodationFragment = new PostNewAccommodationFragment();
//                postNewAccommodationFragment.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewAccommodationFragment, null).commit();
//
////
//            }
//        });

        return view;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void SelectFootwearQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectFootwearSQl = "SELECT itemName, Description, Price, Brand FROM Footwear_Table";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectFootwearSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            itemName.add(rs.getString("itemName"));
                            description.add(rs.getString("Description"));
                            price.add(rs.getString("Price"));
                            brand.add(rs.getString("Brand"));

                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }

}