package com.example.spencer.familymap.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spencer.familymap.Fragments.SearchFragment;
import com.example.spencer.familymap.R;

public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        FragmentManager manager = getFragmentManager();

        SearchFragment search = new SearchFragment();
        FragmentTransaction filterTransaction = manager.beginTransaction();
        filterTransaction.add(R.id.search, search, "search");
        filterTransaction.commit();

    }
}
