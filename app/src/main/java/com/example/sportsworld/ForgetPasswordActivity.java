package com.example.sportsworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText phoneNumberVerification;
    Button resetPasswordButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        progressBar= findViewById(R.id.progressbar_sending_OTP);
        phoneNumberVerification = findViewById(R.id.phoneNumberVerification);
        resetPasswordButton = findViewById(R.id.resetPasswordButton);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//save data into database
                ConnectionHelper c = new ConnectionHelper();
                Connection connection= c.connectionClass();


                //ResultSet resultSet = null;
                try {
                    if (connection != null){

                        String phoneNumberVerification1 = phoneNumberVerification.getText().toString();

                        //query to Insert data nto MSSQL database
                        String selectAccommodationSQl = "SELECT Phone_Number FROM Registration_Table";

                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                        if (rs != null){
                            while (rs.next()){
                                try {
                                    String phnVer1  = rs.getString("Phone_Number");
                                    // String emailVer1 = rs.getString("Email_Address");



                                    if (phoneNumberVerification1.equals(phnVer1)) {

                                        progressBar.setVisibility(View.VISIBLE);
                                        resetPasswordButton.setVisibility(View.INVISIBLE);
                                        try {


                                            PhoneAuthProvider.getInstance().verifyPhoneNumber("+61"+phoneNumberVerification1, 60, TimeUnit.SECONDS,
                                                    ForgetPasswordActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                                        @Override
                                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                            progressBar.setVisibility(View.GONE);
                                                            resetPasswordButton.setVisibility(View.VISIBLE);


                                                        }

                                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                                            progressBar.setVisibility(View.GONE);
                                                            resetPasswordButton.setVisibility(View.VISIBLE);
                                                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onCodeSent(@NonNull String backendOPT, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                            super.onCodeSent(backendOPT, forceResendingToken);
                                                            progressBar.setVisibility(View.GONE);
                                                            resetPasswordButton.setVisibility(View.VISIBLE);

                                                            FragmentManager fragmentManager = getSupportFragmentManager();
                                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                            Bundle bundle = new Bundle();
                                                            bundle.putString("textMobile", phoneNumberVerification1);
                                                            bundle.putString("backendOTP", backendOPT);
                                                            EnterOTPFragment enterOTPFragment = new EnterOTPFragment();
                                                            enterOTPFragment.setArguments(bundle);
                                                            phoneNumberVerification.setText(phoneNumberVerification1);
                                                            fragmentTransaction.replace(R.id.passwordReset, enterOTPFragment, null).commit();
                                                            Toast.makeText(view.getContext(), "OTP has been sent to : " + phoneNumberVerification1, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        } catch (Exception e){
                                            Log.i("Error", e.getMessage());
                                        }

                                    } else {

                                        Toast.makeText(ForgetPasswordActivity.this, "Please Check Phone Number", Toast.LENGTH_SHORT).show();
                                        phoneNumberVerification.setText("");
                                    }
                                } catch (Exception exception){
                                    Log.e("Error", exception.getMessage());
                                }

                            }
                        }
                    }
                }catch (Exception exception){
                    Log.e("Error", exception.getMessage());
                }

                //forgetPasswordQuery();

            }
        });
    }
}