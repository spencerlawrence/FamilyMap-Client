package com.example.spencer.familymap.Tasks;

import android.os.AsyncTask;

import com.example.spencer.familymap.DTOs.LoginRequest;
import com.example.spencer.familymap.Fragments.LoginFragment;

public class LoginTask extends AsyncTask<LoginRequest, Void, String> {

    private LoginFragment.ITaskCallBack callBack;

    public LoginTask(LoginFragment.ITaskCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(LoginRequest... requests) {
        ClientCommunicator communicator = new ClientCommunicator();
        return communicator.login("login", requests[0]);
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        callBack.onTaskCompleted(response);
    }

}
