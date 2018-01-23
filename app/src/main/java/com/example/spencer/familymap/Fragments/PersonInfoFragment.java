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
import android.widget.TextView;

import com.example.spencer.familymap.Activities.MainActivity;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.Person;
import com.example.spencer.familymap.R;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonInfoFragment extends Fragment {

    private String mSelectedID;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mGender;

    public PersonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_info, container, false);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        mSelectedID = bundle.get("selected").toString();
        Person person = Model.findPerson(mSelectedID);
        mFirstName = view.findViewById(R.id.personFirstName);
        mLastName = view.findViewById(R.id.personLastName);
        mGender = view.findViewById(R.id.personGender);
        mFirstName.setText(person.getFirstName());
        mLastName.setText(person.getLastName());
        if(person.getGender().equals("f")) {
            mGender.setText("female");
        } else {
            mGender.setText("male");
        }
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
