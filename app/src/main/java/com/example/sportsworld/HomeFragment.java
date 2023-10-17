package com.example.sportsworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<String> itemName;
    ArrayList<String> description;
    ArrayList<String> price;
    ArrayList<String> brand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHome);
        itemName = new ArrayList<>();
        description = new ArrayList<>();
        price = new ArrayList<>();
        brand= new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectFootwearQuery();
        SelectClothingQuery();
        SelectAccessoriesQuery();
        SelectBagsQuery();

        recyclerView.setAdapter(new RecyclerAdapter(itemName, description, price,brand ));

        return view;
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
                ResultSet rsA = statement.executeQuery(selectFootwearSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            itemName.add(rsA.getString("itemName"));
                            description.add(rsA.getString("Description"));
                            price.add(rsA.getString("Price"));
                            brand.add(rsA.getString("Brand"));

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
    public void SelectClothingQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();

        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectFootwearSQl = "SELECT itemName, Description, Price, Brand FROM Clothing_Table";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectFootwearSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            itemName.add(rsA.getString("itemName"));
                            description.add(rsA.getString("Description"));
                            price.add(rsA.getString("Price"));
                            brand.add(rsA.getString("Brand"));

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
    public void SelectAccessoriesQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectFootwearSQl = "SELECT itemName, Description, Price, Brand FROM Accessories_Table";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectFootwearSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            itemName.add(rsA.getString("itemName"));
                            description.add(rsA.getString("Description"));
                            price.add(rsA.getString("Price"));
                            brand.add(rsA.getString("Brand"));

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
    public void SelectBagsQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectFootwearSQl = "SELECT itemName, Description, Price, Brand FROM Bags_Table";

                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectFootwearSQl);
                if (rsA != null){
                    while (rsA.next()){
                        try {
                            itemName.add(rsA.getString("itemName"));
                            description.add(rsA.getString("Description"));
                            price.add(rsA.getString("Price"));
                            brand.add(rsA.getString("Brand"));

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