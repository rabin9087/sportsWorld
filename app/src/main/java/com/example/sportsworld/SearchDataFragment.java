package com.example.sportsworld;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchDataFragment extends Fragment {

    EditText editSearch;

    ArrayList<String> itemName;
    ArrayList<String> description;
    ArrayList<String> price;
    ArrayList<String> brand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_data, container, false);

        editSearch = view.findViewById(R.id.data);

        RecyclerView recyclerView = view.findViewById(R.id.searchRecycleView);

        ImageButton SearchImageButton = view.findViewById(R.id.searchData);

        SearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemName = new ArrayList<>();
                description = new ArrayList<>();
                price = new ArrayList<>();
                brand= new ArrayList<>();

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

                FootwearQuery();
                ClothingQuery();
                AccessoriesQuery();
                BagsQuery();
                recyclerView.setAdapter(new RecyclerAdapter(itemName, description, price, brand));

            }
        });


        return  view;
    }

    public void FootwearQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){
                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectFootwearSQl = "SELECT itemName, Description, Price, Brand FROM Footwear_Table WHERE " +
                        "Description LIKE '%"+searchFromDatabase1+"%' OR ItemName LIKE '%"+searchFromDatabase1+"%' " +
                        "OR Price LIKE '% "+searchFromDatabase1+ "%' OR Brand LIKE '%"+searchFromDatabase1+"%';";
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
    public void ClothingQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();

        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectClothingSQl = "SELECT itemName, Description, Price, Brand FROM Clothing_Table WHERE " +
                        "Description LIKE '%"+searchFromDatabase1+"%' OR ItemName LIKE '%"+searchFromDatabase1+"%' " +
                        "OR Price LIKE '% "+searchFromDatabase1+ "%' OR Brand LIKE '%"+searchFromDatabase1+"%';";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectClothingSQl);

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
    public void AccessoriesQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectAccessoriesSQl = "SELECT itemName, " +
                        "Description, Price, Brand FROM Accessories_Table WHERE Description LIKE '%"+searchFromDatabase1+"%' " +
                        "OR ItemName LIKE '%"+searchFromDatabase1+"%' OR Price LIKE '% "+searchFromDatabase1+ "%' OR Brand LIKE '%"+searchFromDatabase1+"%';";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectAccessoriesSQl);

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
    public void BagsQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        try {
            if (connection != null){

                //query to Insert data nto MSSQL database


                String searchFromDatabase1 = editSearch.getText().toString();
                String selectBagsSQl = "SELECT ItemName, Description, Price, Brand FROM Bags_Table WHERE" +
                        " Description LIKE '%"+searchFromDatabase1+"%' OR ItemName LIKE '%"+searchFromDatabase1+"%' OR" +
                        " Price LIKE '% "+searchFromDatabase1+ "%' OR Brand LIKE '%"+searchFromDatabase1+"%';";

                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectBagsSQl);
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