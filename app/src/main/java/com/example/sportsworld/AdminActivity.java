package com.example.sportsworld;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class AdminActivity extends AppCompatActivity implements
        FootwearFragment.OnFragmentInteractionListener,
        ClothingFragment.OnFragmentInteractionListener,
        AccessoriesFragment.OnFragmentInteractionListener,
        BagsFragment.OnFragmentInteractionListener
{
    TextView User_FirstName, User_LastName;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView createFootwearButton, postNewClothingButton, postNewAccessoriesButton, postNewBagsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String usernameId1 = bundle.getString("UserName1");
        String value = usernameId1;
        User_FirstName = findViewById(R.id.User_FirstName);
        User_LastName = findViewById(R.id.User_LastName);
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();

        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectFootwearSQl = "SELECT First_Name, Last_Name FROM Registration_Table WHERE User_Name='"+usernameId1+"'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectFootwearSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            User_FirstName.setText(rs.getString("First_Name"));
                            User_LastName.setText(rs.getString("Last_Name"));
                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }

            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }


        navigationView.setNavigationItemSelectedListener(item ->
        {
            switch (item.getItemId()){
                case R.id.nav_home:
                    Log.i("MENU_DRAWER_TAG", "Search item is clicked");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, HomeFragment.class, null)
                            .setReorderingAllowed(true).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

//                case R.id.nav_myPost:
//                    Log.i("MENU_DRAWER_TAG", "MyPost item is clicked");
//                    FragmentManager fragmentManager1 = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("USER", usernameId1);
//                    MyPostFragment myPost = new MyPostFragment();
//                    myPost.setArguments(bundle1);
//                    fragmentTransaction.replace(R.id.fragmentContainerView2, myPost, null).commit();
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                    break;

                case R.id.nav_settings:
                    Log.i("MENU_DRAWER_TAG", "Settings item is clicked");
                    Toast.makeText(this, "You have selected  Setting menu", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.nav_logOut:
                    Log.i("MENU_DRAWER_TAG", "LogOut item is clicked");
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });

        createFootwearButton = findViewById(R.id.createFootwearButton);
        createFootwearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", usernameId1);
                PostNewFootwearFragment postNewFootwearFragment = new PostNewFootwearFragment();
                postNewFootwearFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, postNewFootwearFragment, null).commit();

            }
        });

        postNewClothingButton = findViewById(R.id.createNewClothingButton);
        postNewClothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", usernameId1);
                PostNewClothingFragment postNewClothingFragment = new PostNewClothingFragment();
                postNewClothingFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, postNewClothingFragment, null).commit();

            }
        });
//
        postNewAccessoriesButton = findViewById(R.id.createAccessoriesButton);
        postNewAccessoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", usernameId1);
                PostNewAccessoriesFragment postNewAccessoriesFragment = new PostNewAccessoriesFragment();
                postNewAccessoriesFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, postNewAccessoriesFragment, null).commit();
            }
        });

        postNewBagsButton = findViewById(R.id.createBagsButton);
        postNewBagsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", usernameId1);
                PostNewBagsFragment postNewBagsFragment = new PostNewBagsFragment();
                postNewBagsFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, postNewBagsFragment, null).commit();

            }
        });
    }

    @Override

    public void onFragmentInteraction(Uri uri) {
    }

}
