package com.example.spencer.familymap.Activities;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spencer.familymap.Fragments.MapFragment;
import com.example.spencer.familymap.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        MapFragment map = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putString("selected", getIntent().getStringExtra("selected"));
        map.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.mapActivity, map, "map");
        transaction.commit();
    }
}
