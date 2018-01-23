package com.example.spencer.familymap.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.spencer.familymap.DTOs.LoginRequest;
import com.example.spencer.familymap.DTOs.LoginResponse;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.R;
import com.example.spencer.familymap.Tasks.LoginTask;
import com.example.spencer.familymap.Tasks.DataTask;
import com.example.spencer.familymap.Tasks.RegisterTask;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private IFragmentCallBack callBack;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private RadioButton mMale;
    private RadioButton mFemale;

    public LoginFragment() {
        // Required empty public constructor
    }

    public interface ITaskCallBack {
        void onTaskCompleted(String response);
    }

    public interface IFragmentCallBack {
        void onLogin();
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.callBack = (IFragmentCallBack)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mUsername = view.findViewById(R.id.username);
        mPassword = view.findViewById(R.id.password);
        mFirstName = view.findViewById(R.id.firstName);
        mLastName = view.findViewById(R.id.lastName);
        mEmail = view.findViewById(R.id.email);
        mMale = view.findViewById(R.id.male);
        mFemale = view.findViewById(R.id.female);
        Button loginButton = view.findViewById(R.id.loginButton);
        Button registerButton = view.findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest request = new LoginRequest();
                request.setUserName(mUsername.getText().toString());
                request.setPassword(mPassword.getText().toString());

                // execute Login Async Task
                LoginTask task = new LoginTask(new ITaskCallBack() {
                    @Override
                    public void onTaskCompleted(String response) {
                        onLoginAttempted(response);
                    }
                });
                task.execute(request);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create request object
                LoginRequest request = new LoginRequest();
                request.setUserName(mUsername.getText().toString());
                request.setPassword(mPassword.getText().toString());
                request.setFirstName(mFirstName.getText().toString());
                request.setLastName(mLastName.getText().toString());
                request.setEmail(mEmail.getText().toString());
                if(mMale.isChecked()) request.setGender("m");
                if(mFemale.isChecked()) request.setGender("f");

                // execute Register AsyncTask
                RegisterTask task = new RegisterTask(new ITaskCallBack() {
                    @Override
                    public void onTaskCompleted(String response) {
                        onLoginAttempted(response);
                    }
                });
                task.execute(request);
            }
        });

        return view;
    }

    private void onLoginAttempted(String respBody) {
        Gson gson = new Gson();
        LoginResponse response = gson.fromJson(respBody, LoginResponse.class);

        if(response.getMessage() != null) {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Model.currentUser = response.getPersonID();
            DataTask personTask = new DataTask();
            personTask.execute("person", response.getToken());
            DataTask eventTask = new DataTask();
            eventTask.execute("event", response.getToken());
            callBack.onLogin();
        }
    }

}
