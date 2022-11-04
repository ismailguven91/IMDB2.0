package com.example.application;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragment1 extends Fragment {


    View view;
    EditText theUsername, thePassword;
    TextView wrongUserPass;
    Button button;
    String username1 = "abc";
    String password1 = "123";
    Bundle bundle = new Bundle();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


            view = inflater.inflate(R.layout.fragment_1, container, false);
            button = view.findViewById(R.id.btnGet);


            wrongUserPass = view.findViewById(R.id.wrongUserPass);
            thePassword = view.findViewById(R.id.thePassword);
            theUsername = view.findViewById(R.id.theUsername);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = theUsername.getText().toString().trim();
                String password = thePassword.getText().toString().trim();

                if (username.equals(username1)&& password.equals(password1)){
                    Log.d("LOGIN", "Success");


                    Fragment2 fragment2 = new Fragment2();
                    fragment2.setArguments(bundle);
                    replaceFragment(fragment2);
            }else{
                    wrongUserPass.setText("Wrong username or password");
                }

            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    }


