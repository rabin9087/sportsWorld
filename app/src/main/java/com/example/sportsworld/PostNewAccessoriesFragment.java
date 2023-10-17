package com.example.sportsworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostNewAccessoriesFragment extends Fragment {

    Button PostNewFootwearButton;
    TextView usernameFootwear;
    EditText descriptionFootwear, brandNameFootwear, itemNameFootwear, priceFootwear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_new_accessories, container, false);
        usernameFootwear = view.findViewById(R.id.usernameFootwear);
        Bundle bundle = this.getArguments();
        usernameFootwear.setText(this.getArguments().getString("USER"));


        descriptionFootwear = view.findViewById(R.id.descriptionAccessories);
        brandNameFootwear = view.findViewById(R.id.brandNameAccessories);
        itemNameFootwear = view.findViewById(R.id.itemNameAccessories);
        priceFootwear = view.findViewById(R.id.priceAccessories);

        PostNewFootwearButton = view.findViewById(R.id.PostNewAccessoriesButton);
        PostNewFootwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameFootwear1 = usernameFootwear.getText().toString();
                String descriptionFootwear1 = descriptionFootwear.getText().toString();
                String brandNameFootwear1 = brandNameFootwear.getText().toString();
                String itemNameFootwear1 =itemNameFootwear.getText().toString();
                String priceFootwear1 =priceFootwear.getText().toString();

                if (descriptionFootwear1.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please Describe Your Post", Toast.LENGTH_SHORT).show();

                } else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection = c.connectionClass();

                    try {
                        if (connection != null) {
                            if (usernameFootwear1.isEmpty()){
                                Toast.makeText(view.getContext(), "Please fill current user name", Toast.LENGTH_SHORT).show();
                            } else {

                                //query to Insert data nto MSSQL database
                                String sqlInsertFootwear = "INSERT INTO Accessories_Table (Description, Brand, ItemName, Price) " +
                                        "VALUES ('" + descriptionFootwear1 + "','" + brandNameFootwear1 + "','" + itemNameFootwear1 + "','" + priceFootwear1 + "');";

                                Statement statement = connection.createStatement();

                                Toast.makeText(view.getContext(), "Your Ad has been posted", Toast.LENGTH_SHORT).show();

                                Intent adminActivity = new Intent(getActivity(), AdminActivity.class);
                                adminActivity.putExtra("HA","HA");
                                startActivity(adminActivity);
                                ResultSet rs = statement.executeQuery(sqlInsertFootwear);
                            }

                        } else {
                            Toast.makeText(view.getContext(), "Check Connection", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception exception) {
                        Log.e("Error", exception.getMessage());
                    }
                }
            }
        });

        return view;
    }
}