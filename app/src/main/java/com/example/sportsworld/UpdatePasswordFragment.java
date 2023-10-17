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


public class UpdatePasswordFragment extends Fragment {

    EditText newPassword, confirmNewPassword;
    TextView mobile;
    Button confirmPasswordButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_password, container, false);

        newPassword = view.findViewById(R.id.newPassword);
        confirmNewPassword = view.findViewById(R.id.confirmNewPassword);

        mobile = view.findViewById(R.id.mobile);
        Bundle bundle = this.getArguments();
        assert bundle != null;
        String myTextMobile = bundle.getString("mobileNumber");
        mobile.setText(myTextMobile);

        confirmPasswordButton = view.findViewById(R.id.confirmPasswordButton);
        confirmPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newPassword1 = newPassword.getText().toString();
                String confirmNewPassword1 = confirmNewPassword.getText().toString();

                if (newPassword1.equals(confirmNewPassword1)) {

                    updatePasswordQuery();

                    Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                    startActivity(mainActivity);
                    Toast.makeText(view.getContext(), "Your Password has been reset", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    newPassword.setText("");
                    confirmNewPassword.setText("");
                }
            }
        });



        return view;
    }

    public void updatePasswordQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){


                String newPassword1 = newPassword.getText().toString();
                String confirmNewPassword2 = confirmNewPassword.getText().toString();
                String mobile1 = mobile.getText().toString();
                //query to Insert data nto MSSQL database
                String updatePasswordQuery = "UPDATE Registration_Table SET Password = '"+newPassword1 +"'," +
                        " Confirm_Password = '"+confirmNewPassword2 +"' WHERE Phone_Number = '"+mobile1 +"' OR Email_Address = '"+mobile1 +"';";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(updatePasswordQuery);

            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }
}