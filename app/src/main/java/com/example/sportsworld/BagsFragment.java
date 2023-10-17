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


public class BagsFragment extends Fragment {
    TextView usernameValueBags;

    ArrayList<String> itemName;
    ArrayList<String> description;
    ArrayList<String> price;
    ArrayList<String> brand;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_bags, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBags);
        usernameValueBags = view.findViewById(R.id.usernameValueBags);
        assert this.getArguments() != null;
        usernameValueBags.setText(this.getArguments().getString("USER"));


        itemName = new ArrayList<>();
        description = new ArrayList<>();
        price = new ArrayList<>();
        brand= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        SelectBagsQuery();

        recyclerView.setAdapter(new RecyclerAdapter(itemName, description, price, brand));


//        createRestaurantButton = view.findViewById(R.id.createRestaurantButton);
//        createRestaurantButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bundle bundle = new Bundle();
//                String userName = usernameValueRestaurant.getText().toString();
//                bundle.putString("createUserNameRestaurant", userName);
//                PostNewRestaurantFragment postNewRestaurantFragment = new PostNewRestaurantFragment();
//                postNewRestaurantFragment.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewRestaurantFragment, null).commit();
//
////                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
////                fragmentTransaction.replace(R.id.fragmentContainerView2, new PostNewRestaurantFragment()).commit();
//            }
//        });


        return view;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void SelectBagsQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Select data from MSSQL database
                String selectBagsSQl = "SELECT itemName, Description, Price, Brand FROM Bags_Table";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectBagsSQl);

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