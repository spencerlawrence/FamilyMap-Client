package com.example.spencer.familymap.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spencer.familymap.Fragments.SettingsFragment;
import com.example.spencer.familymap.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FragmentManager manager = getFragmentManager();

        SettingsFragment settings = new SettingsFragment();
        FragmentTransaction filterTransaction = manager.beginTransaction();
        filterTransaction.add(R.id.settings, settings, "settings");
        filterTransaction.commit();
    }
}
