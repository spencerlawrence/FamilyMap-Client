package com.example.spencer.familymap.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.Fragments.LoginFragment;
import com.example.spencer.familymap.Fragments.MapFragment;
import com.example.spencer.familymap.R;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class MainActivity extends AppCompatActivity implements LoginFragment.IFragmentCallBack {

    public static boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();

        Iconify.with(new FontAwesomeModule());
        if (!loggedIn) {
            LoginFragment login = new LoginFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container, login, "login");
            transaction.commit();
        } else {
            MapFragment map = new MapFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("selected", "false");
            map.setArguments(bundle);
            transaction.replace(R.id.container, map, "map");
            transaction.commit();
        }
    }

    @Override
    public void onLogin() {
        loggedIn = true;
        Model.turnOnFilters();
        MapFragment map = new MapFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("selected", "false");
        map.setArguments(bundle);
        transaction.replace(R.id.container, map, "map");
        transaction.commit();
    }
}