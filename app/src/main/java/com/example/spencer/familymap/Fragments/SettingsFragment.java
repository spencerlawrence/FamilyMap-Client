package com.example.spencer.familymap.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.spencer.familymap.Activities.MainActivity;
import com.example.spencer.familymap.DTOs.Event;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.Person;
import com.example.spencer.familymap.R;
import com.google.android.gms.maps.GoogleMap;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private RadioButton mSatellite;
    private RadioButton mHybrid;
    private RadioButton mNormal;
    private RelativeLayout mLogout;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setHasOptionsMenu(true);

        mSatellite = view.findViewById(R.id.satellite);
        mSatellite.setChecked(Model.mMapType.equals("satellite"));
        mHybrid = view.findViewById(R.id.hybrid);
        mHybrid.setChecked(Model.mMapType.equals("hybrid"));
        mNormal = view.findViewById(R.id.normal);
        mNormal.setChecked(Model.mMapType.equals("normal"));

        mLogout = view.findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.loggedIn = false;
                Model.events = new ArrayList<Event>();
                Model.persons = new ArrayList<Person>();
                Model.mMotherSide = new ArrayList<Person>();
                Model.mFatherSide = new ArrayList<Person>();
                Model.turnOnFilters();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSatellite.isChecked()) Model.mMapType = "satellite";
            }
        });
        mHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSatellite.isChecked()) Model.mMapType = "satellite";
            }
        });
        mNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSatellite.isChecked()) Model.mMapType = "satellite";
            }
        });



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_go_to_top, menu);
        MenuItem goToTopMenuItem = menu.findItem(R.id.goToTopButton);
        goToTopMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_angle_double_up)
                .actionBarSize()
                .colorRes(R.color.menu)
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(getActivity(), MainActivity.class));
        return true;
    }

}
