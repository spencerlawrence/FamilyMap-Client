package com.example.spencer.familymap.Tasks;

import android.os.AsyncTask;

import com.example.spencer.familymap.DTOs.EventResponse;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.PersonResponse;
import com.google.gson.Gson;

public class DataTask extends AsyncTask<String, Void, String> {

    private String mApi;

    @Override
    protected String doInBackground(String... strings) {
        ClientCommunicator communicator = new ClientCommunicator();
        if(strings[0].equals("person")) {
            mApi = "person";
            return communicator.getData("person", strings[1]);
        } else {
            mApi = "event";
            return communicator.getData("event", strings[1]);
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        Gson gson = new Gson();
        if(mApi.equals("person")) {
            PersonResponse personResponse = gson.fromJson(response, PersonResponse.class);
            Model.setPersons(personResponse.getPersons());
        } else {
            EventResponse eventResponse = gson.fromJson(response, EventResponse.class);
            Model.setEvents(eventResponse.getEvents());
        }
    }
}
