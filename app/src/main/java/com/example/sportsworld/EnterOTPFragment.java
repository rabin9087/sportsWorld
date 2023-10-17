package com.example.sportsworld;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOTPFragment extends Fragment {

    TextView textMobile;
    Button getOPTButton;
    EditText inputotp1, inputotp2, inputotp3, inputotp4, inputotp5, inputotp6;
    String getOTPBackend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_o_t_p, container, false);

        textMobile = view.findViewById(R.id.textMobile);

        inputotp1= view.findViewById(R.id.inputotp1);
        inputotp2= view.findViewById(R.id.inputotp2);
        inputotp3= view.findViewById(R.id.inputotp3);
        inputotp4= view.findViewById(R.id.inputotp4);
        inputotp5= view.findViewById(R.id.inputotp5);
        inputotp6= view.findViewById(R.id.inputotp6);


        Bundle bundle = this.getArguments();
        assert bundle != null;
        String myTextMobile = bundle.getString("textMobile");
        getOTPBackend = bundle.getString("backendOTP");
        textMobile.setText(myTextMobile);

        ProgressBar progressBarVerifyOTP = view.findViewById(R.id.progressbar_verify_otp);
        getOPTButton = view.findViewById(R.id.getOPTButton);
        getOPTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if ( !inputotp1.getText().toString().trim().isEmpty() && !inputotp2.getText().toString().trim().isEmpty() &&
                        !inputotp3.getText().toString().trim().isEmpty() && !inputotp4.getText().toString().trim().isEmpty() &&
                        !inputotp5.getText().toString().trim().isEmpty() && !inputotp6.getText().toString().trim().isEmpty()){

                    String enterCodeOTP =
                            inputotp1.getText().toString() +
                                    inputotp2.getText().toString() +
                                    inputotp3.getText().toString() +
                                    inputotp4.getText().toString() +
                                    inputotp5.getText().toString() +
                                    inputotp6.getText().toString();

                    if (getOTPBackend != null){
                        progressBarVerifyOTP.setVisibility(View.VISIBLE);
                        getOPTButton.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getOTPBackend,enterCodeOTP);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBarVerifyOTP.setVisibility(View.GONE);
                                getOPTButton.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()){
                                    Bundle bundle = new Bundle();
                                    bundle.putString("mobileNumber", myTextMobile);
                                    UpdatePasswordFragment updatePasswordFragment = new UpdatePasswordFragment();
                                    updatePasswordFragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.passwordReset, updatePasswordFragment, null).commit();
                                } else {
                                    Toast.makeText(view.getContext(), "Enter the Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(view.getContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(view.getContext(), "OTP verified", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberOptMove();

        return view;
    }

    private void numberOptMove() {
        inputotp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    inputotp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    inputotp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    inputotp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    inputotp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputotp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()){
                    inputotp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}