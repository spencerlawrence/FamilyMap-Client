package com.example.spencer.familymap.Fragments;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.spencer.familymap.Activities.FilterActivity;
import com.example.spencer.familymap.Activities.MainActivity;
import com.example.spencer.familymap.Activities.PersonActivity;
import com.example.spencer.familymap.Activities.SearchActivity;
import com.example.spencer.familymap.Activities.SettingsActivity;
import com.example.spencer.familymap.DTOs.Event;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.Person;
import com.example.spencer.familymap.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, View.OnTouchListener {

    private MapView mMapView;
    private View mView;
    private String mSelectedID;
    private String mSelectedEvent;
    private Marker mSelectedMarker;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        Bundle bundle = getArguments();
        mSelectedEvent = bundle.get("selected").toString();
        setHasOptionsMenu(true);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = mView.findViewById(R.id.map);
        if(mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        String ID = Model.currentUser;
        Person user = Model.findPerson(ID);
        System.out.print(user);
        Model.setSides(user);

        Map<String, Float> colors = new HashMap<>();
        Set<Float> usedColors = new HashSet<>();
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(Model.mapType());
        googleMap.setOnMarkerClickListener(this);
        for(int i = 0; i < Model.getEvents().size(); i++) {
            Event event = Model.getEvents().get(i);
            if(Model.filter(event)) {
                String type = event.getEventType();
                Float color = 0.1f;
                if (colors.containsKey(type)) {
                    color = colors.get(type);
                } else {
                    if (!usedColors.contains(BitmapDescriptorFactory.HUE_BLUE))
                        color = BitmapDescriptorFactory.HUE_BLUE;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_GREEN))
                        color = BitmapDescriptorFactory.HUE_GREEN;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_ORANGE))
                        color = BitmapDescriptorFactory.HUE_ORANGE;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_RED))
                        color = BitmapDescriptorFactory.HUE_RED;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_YELLOW))
                        color = BitmapDescriptorFactory.HUE_YELLOW;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_VIOLET))
                        color = BitmapDescriptorFactory.HUE_VIOLET;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_CYAN))
                        color = BitmapDescriptorFactory.HUE_CYAN;
                    else if (!usedColors.contains(BitmapDescriptorFactory.HUE_MAGENTA))
                        color = BitmapDescriptorFactory.HUE_MAGENTA;
                    colors.put(event.getEventType(), color);
                    usedColors.add(color);
                }
                Marker marker;
                marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(event.getLatitude(), event.getLongitude()))
                        .snippet(event.getEventID())
                        .icon(BitmapDescriptorFactory.defaultMarker(Float.valueOf(color))));
                marker.setTag(0);

                if (!mSelectedEvent.equals("false")) {
                    if (mSelectedEvent.equals(event.getEventID())) {
                        mSelectedMarker = marker;
                        System.out.print(mSelectedMarker.toString());
                    }
                }
            }
        }
        if (!"false".equals(mSelectedEvent)) {
            onMarkerClick(mSelectedMarker);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Event event = Model.findEvent(marker.getSnippet());
        RelativeLayout layout = mView.findViewById(R.id.details);
        TextView textView = mView.findViewById(R.id.mapFragmentText);
        Person person = Model.findPerson(event.getPersonID());
        textView.setText(person.getFirstName() + " " + person.getLastName() + "\n"
                + event.getEventType() + ": " + event.getCity() + ", " +
                event.getCountry() +" (" + event.getYear() + ")");
        IconTextView icon = mView.findViewById(R.id.mapFragmentIcon);
        if(person.getGender().equals("f")) icon.setText("{fa-female}");
        else icon.setText("{fa-male}");
        mSelectedID = person.getPersonID();
        layout.setOnTouchListener(this);
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intent intent = new Intent(getActivity(), PersonActivity.class);
        intent.putExtra("selected", mSelectedID);
        startActivity(intent);
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if("false".equals(mSelectedEvent)) {
            inflater.inflate(R.menu.menu_map, menu);
            MenuItem searchMenuItem = menu.findItem(R.id.search);

            searchMenuItem.setIcon(
                    new IconDrawable(getActivity(), FontAwesomeIcons.fa_search)
                            .actionBarSize()
                            .colorRes(R.color.menu)
            );

            MenuItem filterMenuItem = menu.findItem(R.id.filter);
            filterMenuItem.setIcon(
                    new IconDrawable(getActivity(), FontAwesomeIcons.fa_filter)
                            .actionBarSize()
                            .colorRes(R.color.menu)
            );

            MenuItem settingsMenuItem = menu.findItem(R.id.settings);
            settingsMenuItem.setIcon(
                    new IconDrawable(getActivity(), FontAwesomeIcons.fa_gear)
                            .actionBarSize()
                            .colorRes(R.color.menu)
            );

        } else {
            inflater.inflate(R.menu.menu_go_to_top, menu);

            MenuItem goToTopMenuItem = menu.findItem(R.id.goToTopButton);
            goToTopMenuItem.setIcon(
                    new IconDrawable(getActivity(), FontAwesomeIcons.fa_angle_double_up)
                            .actionBarSize()
                            .colorRes(R.color.menu)
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.filter:
                startActivity(new Intent(getActivity(), FilterActivity.class));
                break;
            case R.id.search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.goToTopButton:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
        return true;
    }

}
