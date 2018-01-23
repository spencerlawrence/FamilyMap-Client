package com.example.spencer.familymap.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spencer.familymap.DTOs.Event;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.Person;
import com.example.spencer.familymap.R;
import com.example.spencer.familymap.RecyclerView.MyAdapter;
import com.example.spencer.familymap.RecyclerView.RecyclerViewChild;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    private View mView;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<RecyclerViewChild> mMyDataset = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        SearchView searchView = view.findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(this);

        Set<String> alreadyAdded= new HashSet<String>();
        for(int i = 0; i < Model.events.size(); i++) {
            String eventInfo;

            Event e = Model.events.get(i);
            if(Model.filter(e)){
                if(alreadyAdded.contains(e.getPersonID())) {
                    //do nothing
                } else {
                    alreadyAdded.add(e.getPersonID());
                    Person p = Model.findPerson(e.getPersonID());
                    String personInfo = p.getFirstName() + " " + p.getLastName();
                    if(p.getGender().equals("m")) {
                        RecyclerViewChild child = new RecyclerViewChild("{fa-male}", personInfo, "person", p.getPersonID());
                        mMyDataset.add(child);
                    } else {
                        RecyclerViewChild child = new RecyclerViewChild("{fa-male}", personInfo, "person", p.getPersonID());
                        mMyDataset.add(child);
                    }
                }
                eventInfo = e.getEventType() + ": " + e.getCity() + ", " + e.getCountry() +
                        " (" + e.getYear() + ")\n" + Model.findPerson(e.getPersonID()).getFirstName() + " " + Model.findPerson(e.getPersonID()).getLastName();
                RecyclerViewChild child = new RecyclerViewChild("{fa-map-marker}", eventInfo, "event", e.getEventID());
                mMyDataset.add(child);
            }
        }



        mRecyclerView = view.findViewById(R.id.searchResults);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(getActivity(), mMyDataset);
        mRecyclerView.setAdapter(mAdapter);

        mView = view;
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<RecyclerViewChild> newList = new ArrayList<>();

        for(RecyclerViewChild child : mMyDataset) {
            String info = child.getInfo().toLowerCase();
            if(info.contains(newText)) newList.add(child);
        }
        mAdapter.setFilter(newList);
        return true;
    }
}
