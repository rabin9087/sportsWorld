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


public class RegisterFragment extends Fragment {

    TextView fillFields;
    EditText firstName, lastName, userName, password,confirmPassword, emailAddress, phoneNumber, address;
    Button registerButton, gotAnAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Finding all the TextView, EditView and Buttons
        firstName=view.findViewById(R.id.firstName);
        lastName=view.findViewById(R.id.lastName);
        userName=view.findViewById(R.id.userName);
        password=view.findViewById(R.id.password);
        confirmPassword=view.findViewById(R.id.confirmPassword);
        emailAddress=view.findViewById(R.id.emailAddress);
        phoneNumber=view.findViewById(R.id.phoneNumber);
        address=view.findViewById(R.id.address);

        registerButton=view.findViewById(R.id.registerButton);
        gotAnAccount = view.findViewById(R.id.gotAnAccount);
        fillFields = view.findViewById(R.id.fillFields);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName1 = firstName.getText().toString();
                String lastName1 = lastName.getText().toString();
                String userName1 = userName.getText().toString();
                String password1 = password.getText().toString();
                String confirmPassword1 = confirmPassword.getText().toString();
                String emailAddress1 = emailAddress.getText().toString();
                String phoneNumber1 = phoneNumber.getText().toString();
                String address1 = phoneNumber.getText().toString();

                if (firstName1.isEmpty() || lastName1.isEmpty() || userName1.isEmpty()
                        || password1.isEmpty() || confirmPassword1.isEmpty() || emailAddress1.isEmpty() || phoneNumber1.isEmpty() ){
                    fillFields.setText("Please fill in all fields!");

                } else if (!password1.equals(confirmPassword1)){
                    fillFields.setText("Please confirm Password");
                    password.setText("");
                    confirmPassword.setText("");
                }
                else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection= c.connectionClass();
                    //ResultSet resultSet = null;
                    try {
                        if (connection != null){

                            //query to Insert data nto MSSQL database
                            String sqlInsert = "INSERT INTO Registration_Table VALUES ('"+firstName1+"','"+lastName1+
                                    "','"+userName1+"','"+password1+"','"+confirmPassword1+"','"+emailAddress1+"','"+phoneNumber1+"','"+address1+"');";
                            Statement statement = connection.createStatement();
                            firstName.setText("");lastName.setText("");userName.setText("");
                            password.setText(""); confirmPassword.setText("");emailAddress.setText("");phoneNumber.setText("");address.setText("");
                            Toast.makeText(view.getContext(), "Account has been successfully registered", Toast.LENGTH_SHORT).show();

                            Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                            startActivity(mainActivity);


                            ResultSet rs = statement.executeQuery(sqlInsert);

                        } else {
                            Toast.makeText(view.getContext(), "Check Connection", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception exception){
                        Log.e("Error", exception.getMessage());
                    }
                }
            }
        });

        gotAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
        return view;
    }
}