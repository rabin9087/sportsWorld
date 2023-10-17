package com.example.sportsworld;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

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

public class AccessoriesFragment extends Fragment {
    TextView usernameValueAccessories;
    ArrayList<String> itemName;
    ArrayList<String> description;
    ArrayList<String> price;
    ArrayList<String> brand;

    ImageButton createGroceryStoreButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_accessories, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAccessories);
        usernameValueAccessories = view.findViewById(R.id.usernameValueAccessories);
        assert this.getArguments() != null;
        usernameValueAccessories.setText(this.getArguments().getString("USER"));

        itemName = new ArrayList<>();
        description = new ArrayList<>();
        price = new ArrayList<>();
        brand= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectAccessoriesQuery();

        recyclerView.setAdapter(new RecyclerAdapter(itemName, description, price, brand));

        return view;

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void SelectAccessoriesQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccessoriesSQl = "SELECT itemName, Description, Price, Brand FROM Accessories_Table";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccessoriesSQl);

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