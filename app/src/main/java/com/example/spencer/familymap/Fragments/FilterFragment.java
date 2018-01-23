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
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.spencer.familymap.Activities.MainActivity;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    private ToggleButton mBaptism;
    private ToggleButton mBirth;
    private ToggleButton mDeath;
    private ToggleButton mMarriage;
    private ToggleButton mFatherSide;
    private ToggleButton mMotherSide;
    private ToggleButton mMaleEvents;
    private ToggleButton mFemaleEvents;

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        setHasOptionsMenu(true);
        mBaptism = view.findViewById(R.id.baptismToggle);
        mBaptism.setChecked(Model.mFilterBaptism);
        mBirth = view.findViewById(R.id.birthToggle);
        mBirth.setChecked(Model.mFilterBirth);
        mDeath = view.findViewById(R.id.deathToggle);
        mDeath.setChecked(Model.mFilterDeath);
        mMarriage = view.findViewById(R.id.marriageToggle);
        mMarriage.setChecked(Model.mFilterMarriage);
        mMotherSide = view.findViewById(R.id.motherSideToggle);
        mMotherSide.setChecked(Model.mFilterMotherSide);
        mFatherSide = view.findViewById(R.id.fatherSideToggle);
        mFatherSide.setChecked(Model.mFilterFatherSide);
        mFemaleEvents = view.findViewById(R.id.femaleToggle);
        mFemaleEvents.setChecked(Model.mFilterFemaleEvents);
        mMaleEvents = view.findViewById(R.id.maleToggle);
        mMaleEvents.setChecked(Model.mFilterMaleEvents);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBaptism.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterBaptism = true;
                    Model.filteredEvents.add("baptism");
                } else {
                    if(Model.filteredEvents.contains("baptism")) {
                        Model.filteredEvents.remove("baptism");
                        Model.mFilterBaptism = false;
                    }
                }
            }
        });

        mBirth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterBirth = true;
                    Model.filteredEvents.add("birth");
                } else {
                    if(Model.filteredEvents.contains("birth")) {
                        Model.filteredEvents.remove("birth");
                        Model.mFilterBirth = false;
                    }
                }
            }
        });


        mDeath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterDeath = true;
                    Model.filteredEvents.add("death");
                } else {
                    if(Model.filteredEvents.contains("death")) {
                        Model.filteredEvents.remove("death");
                        Model.mFilterDeath = false;
                    }
                }
            }
        });

        mMarriage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterMarriage = true;
                    Model.filteredEvents.add("marriage");
                } else {
                    if(Model.filteredEvents.contains("marriage")) {
                        Model.filteredEvents.remove("marriage");
                        Model.mFilterMarriage = false;
                    }
                }
            }
        });

        mMotherSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterMotherSide = true;
                } else {
                    Model.mFilterMotherSide = false;
                }
            }
        });

        mFatherSide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterFatherSide = true;
                } else {
                    Model.mFilterFatherSide = false;
                }
            }
        });

        mFemaleEvents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterFemaleEvents = true;
                } else {
                    Model.mFilterFemaleEvents = false;
                }
            }
        });

        mMaleEvents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Model.mFilterMaleEvents = true;
                } else {
                    Model.mFilterMaleEvents = false;
                }
            }
        });
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
