package com.example.sportsworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    TextView textView11;
    EditText UserName, Password;
    Button LogInButton, RegisterButton, forgotPasswordButton;
    ConnectionHelper c = new ConnectionHelper();
    Connection connection= c.connectionClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterButton = findViewById(R.id.RegisterButton);
        LogInButton = findViewById(R.id.LogInButton);
        UserName = findViewById(R.id.UserName);
        Password = findViewById(R.id.PasswordLogIn);
        textView11 = findViewById(R.id.textView11);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new RegisterFragment()).commit();
            }
        });

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName1 = UserName.getText().toString();
                String Password1 = Password.getText().toString();
                if (UserName1.isEmpty() || Password1.isEmpty()) {
                    textView11.setText("Please fill in all fields!");
                }
                else if(UserName1.equals("admin") || Password1.equals("admin")){
                    Intent adminActivity = new Intent(MainActivity.this, AdminActivity.class);
                    String UserName2 = UserName.getText().toString();
                    adminActivity.putExtra("UserName1", UserName1);
                    startActivity(adminActivity);
                    finish();
                }
                else {
                    CheckLogin checkLogin= new CheckLogin();
                    checkLogin.execute("");
                }
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new ForgetFragment()).commit();
            }
        });
    }
    public class CheckLogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                //Query from SQL
                if (connection != null){

                    String logInSql = "SELECT User_Name, Password  FROM Registration_Table WHERE User_Name= '"+UserName.getText()+"' AND Password = '"+Password.getText()+"'";
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(logInSql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            }
                        });
                        z = "success";

                        Intent homeActivity = new Intent(MainActivity.this, HomeActivity.class);
                        String UserName1 = UserName.getText().toString();
                        homeActivity.putExtra("UserName1", UserName1);
                        startActivity(homeActivity);
                        finish();
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Check username and password", Toast.LENGTH_SHORT).show();
                                //UserName.setText("");
                                Password.setText("");
                            }
                        });
                    }
                }

            }catch ( Exception e){
                isSuccess = false;
                Log.e("SQL Error :",e.getMessage());
            }

            return z;
        }

    }
}