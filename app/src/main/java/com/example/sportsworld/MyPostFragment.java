package com.example.sportsworld;

import android.annotation.SuppressLint;
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
import java.util.ArrayList;

public class MyPostFragment extends Fragment {

    TextView usernameValueMyPost;
    TextView FName, LName, UName, PNumber, EMail, AddressUser;
    EditText updateAddress;
    Button updateAddressButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);

        FName = view.findViewById(R.id.FName);
        LName = view.findViewById(R.id.LName);
        UName= view.findViewById(R.id.UName);
        PNumber = view.findViewById(R.id.PNumber);
        EMail = view.findViewById(R.id.EMail);
        AddressUser= view.findViewById(R.id.AddressUser);
        updateAddress = view.findViewById(R.id.updatedAddress);
        updateAddressButton = view.findViewById(R.id.updateAddressButton);

        usernameValueMyPost = view.findViewById(R.id.usernameValueMyPost);
        assert this.getArguments() != null;
        usernameValueMyPost.setText(this.getArguments().getString("USER"));

        MyProfileQuery();


        updateAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateAddress2 = updateAddress.getText().toString();
                if(updateAddress2.equals("")){
                    Toast.makeText(getContext(), "Please enter your Address", Toast.LENGTH_SHORT).show();
                } else {
                    upDateAddressQuery();
                    MyProfileQuery();
                }
            }
        });

     return view;
  }

    public void MyProfileQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String selectFootwearSQl = "SELECT First_Name, Last_Name, User_Name, Phone_Number, Email_Address, Address FROM Registration_Table WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectFootwearSQl);

                if (rs != null) {
                    while (rs.next()) {
                        try {
                            FName.setText(rs.getString("First_Name"));
                            LName.setText(rs.getString("Last_Name"));
                            UName.setText(rs.getString("User_Name"));
                            PNumber.setText(rs.getString("Phone_Number"));
                            EMail.setText(rs.getString("Email_Address"));
                            AddressUser.setText(rs.getString("Address"));

                        } catch (Exception exception) {
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }

    public void upDateAddressQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String updateAddress1 = updateAddress.getText().toString();
                String upDateAddressSQL = "UPDATE Registration_Table SET Address = '"+ updateAddress1 +" ' WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();

                Toast.makeText(getContext(), "Your Address has been updated", Toast.LENGTH_SHORT).show();

                ResultSet rs = statement.executeQuery(upDateAddressSQL);

            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }
}