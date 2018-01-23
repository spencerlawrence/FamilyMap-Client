package com.example.spencer.familymap.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.spencer.familymap.RecyclerView.MyExpandableAdapter;
import com.example.spencer.familymap.DTOs.Event;
import com.example.spencer.familymap.DTOs.Model;
import com.example.spencer.familymap.DTOs.Person;
import com.example.spencer.familymap.RecyclerView.RecyclerViewChild;
import com.example.spencer.familymap.RecyclerView.RecyclerViewParent;
import com.example.spencer.familymap.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDetailsFragment extends Fragment {

    private String mSelectedID;

    public PersonDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_details, container, false);

        Bundle bundle = getArguments();
        mSelectedID = bundle.get("selected").toString();
        MyExpandableAdapter adapter = new MyExpandableAdapter(getActivity(), generate());
        adapter.setCustomParentAnimationViewId(R.id.expandArrow);
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        RecyclerView recyclerView = view.findViewById(R.id.lifeEvents);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        return view;
    }

    private ArrayList<ParentObject> generate() {
        ArrayList<Event> events = Model.findAllEvents(mSelectedID);
        Person person = Model.findPerson(mSelectedID);
        ArrayList<ParentObject> parentObjects = new ArrayList<>();

        RecyclerViewParent lifeEvents = new RecyclerViewParent();
        lifeEvents.setTitle("LIFE EVENTS");
        ArrayList<Object> eventList = new ArrayList<>();
        for (Event event : events) {
            String info = event.getEventType() + ": " + event.getCity() + ", " + event.getCountry() +
                    " (" + event.getYear() + ")\n" + Model.findPerson(mSelectedID).getFirstName() + " " + Model.findPerson(mSelectedID).getLastName();
            eventList.add(new RecyclerViewChild("{fa-map-marker}", info, "event", event.getEventID()));
        }
        lifeEvents.setChildObjectList(eventList);
        parentObjects.add(lifeEvents);

        RecyclerViewParent family = new RecyclerViewParent();
        family.setTitle("FAMILY");
        ArrayList<Object> familyList = new ArrayList<>();
        String fatherID = person.getFather();
        String spouseID = person.getSpouse();
        if(fatherID != null && !fatherID.isEmpty() && !fatherID.equals("null")) {
            Person father = Model.findPerson(person.getFather());
            String fatherInfo = father.getFirstName() + " " + father.getLastName() + "\nfather";
            familyList.add(new RecyclerViewChild("{fa-male}", fatherInfo, "person", father.getPersonID()));

            Person mother = Model.findPerson(person.getMother());
            String motherInfo = mother.getFirstName() + " " + mother.getLastName() + "\nmother";
            familyList.add(new RecyclerViewChild("{fa-female}", motherInfo, "person", mother.getPersonID()));

        } if(spouseID != null && !spouseID.isEmpty() && !spouseID.equals("null")) {
            Person spouse = Model.findPerson(person.getSpouse());
            if(spouse.getGender().equals("f")) {
                String spouseInfo = spouse.getFirstName() + " " + spouse.getLastName() + "\nwife";
                familyList.add(new RecyclerViewChild("{fa-female}", spouseInfo, "person", spouse.getPersonID()));
            } else {
                String spouseInfo = spouse.getFirstName() + " " + spouse.getLastName() + "\nhusband";
                familyList.add(new RecyclerViewChild("{fa-male}", spouseInfo, "person", spouse.getPersonID()));
            }
        }
        family.setChildObjectList(familyList);
        parentObjects.add(family);

        return parentObjects;
    }

}
