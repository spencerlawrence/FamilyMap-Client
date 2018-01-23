package com.example.spencer.familymap.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spencer.familymap.Fragments.FilterFragment;
import com.example.spencer.familymap.R;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        FragmentManager manager = getFragmentManager();

        FilterFragment filter = new FilterFragment();
        FragmentTransaction filterTransaction = manager.beginTransaction();
        filterTransaction.add(R.id.filter, filter, "filter");
        filterTransaction.commit();
    }
}
