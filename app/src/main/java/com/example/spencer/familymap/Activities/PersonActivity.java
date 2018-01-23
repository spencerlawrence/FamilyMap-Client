package com.example.spencer.familymap.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spencer.familymap.Fragments.PersonDetailsFragment;
import com.example.spencer.familymap.Fragments.PersonInfoFragment;
import com.example.spencer.familymap.R;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);


        Bundle bundle = new Bundle();
        bundle.putString("selected", getIntent().getStringExtra("selected"));

        FragmentManager manager = getFragmentManager();

        PersonInfoFragment info = new PersonInfoFragment();
        info.setArguments(bundle);
        FragmentTransaction infoTransaction = manager.beginTransaction();
        infoTransaction.add(R.id.person, info, "mInfo");
        infoTransaction.commit();

        PersonDetailsFragment details = new PersonDetailsFragment();
        details.setArguments(bundle);
        FragmentTransaction detailsTransaction = manager.beginTransaction();
        detailsTransaction.add(R.id.person, details, "details");
        detailsTransaction.commit();
    }
}
