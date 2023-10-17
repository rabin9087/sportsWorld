package com.example.sportsworld;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String userName, password, port, databaseName;
    Connection connection = null;
    String ConnectionURL;
    @SuppressLint("NewApi")
    public Connection connectionClass(){
        //ip= "192.168.20.11";
        databaseName = "SportsWorld";
        userName="sa";
        password="Jackson@9087";
        port= "1433";

        StrictMode.ThreadPolicy policy;
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ConnectionURL ="jdbc:jtds:sqlserver://172.20.10.2;databaseName=SportsWorld";

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(ConnectionURL,userName,password);


        }catch (SQLException | ClassNotFoundException exception){
            Log.e("Error", exception.getMessage());
        }
        return connection;
    }
}
