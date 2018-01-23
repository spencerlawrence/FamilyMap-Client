package com.example.spencer.familymap.DTOs;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class Model {
    public static ArrayList<Event> events;
    public static ArrayList<Person> persons;
    public static String currentUser;
    public static ArrayList<Person> mFatherSide = new ArrayList<>();
    public static ArrayList<Person> mMotherSide = new ArrayList<>();
    public static String mMapType;

    public static int mapType() {
        if(mMapType.equals("satellite")) {
            return GoogleMap.MAP_TYPE_SATELLITE;
        } else if(mMapType.equals("hybrid")) {
            return GoogleMap.MAP_TYPE_HYBRID;
        } else {
            return GoogleMap.MAP_TYPE_NORMAL;
        }
    }

    public static Event findEvent(String ID) {
        for(int i = 0; i < events.size(); i++) {
            if(events.get(i).getEventID().equals(ID)) return events.get(i);
        }
        return null;
    }

    public static ArrayList<Event> findAllEvents(String ID) {
        ArrayList<Event> eventsByPerson = new ArrayList<>();
        for(int i = 0; i < events.size(); i++) {
            if(events.get(i).getPersonID().equals(ID)) eventsByPerson.add(events.get(i));
        }
        return eventsByPerson;
    }

    public static Person findPerson(String ID) {
        for(int i = 0; i < persons.size(); i++) {
            if(persons.get(i).getPersonID().equals(ID)) return persons.get(i);
        }
        return null;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<Event> events) {
        Model.events = events;
    }

    public static ArrayList<Person> getPersons() {
        return persons;
    }

    public static void setPersons(ArrayList<Person> persons) {
        Model.persons = persons;
    }

    public static ArrayList<String> filteredEvents = new ArrayList<>();

    public static boolean mFilterBaptism;
    public static boolean mFilterBirth;
    public static boolean mFilterMarriage;
    public static boolean mFilterDeath;

    public static boolean mFilterFatherSide;
    public static boolean mFilterMotherSide;
    public static boolean mFilterMaleEvents;
    public static boolean mFilterFemaleEvents;

    public static void turnOnFilters() {
        mFilterBaptism = true;
        mFilterBirth = true;
        mFilterMarriage = true;
        mFilterDeath = true;
        mFilterFatherSide = true;
        mFilterMotherSide = true;
        mFilterMaleEvents = true;
        mFilterFemaleEvents = true;
        filteredEvents.add("birth");
        filteredEvents.add("baptism");
        filteredEvents.add("death");
        filteredEvents.add("marriage");
        mMapType = "normal";
    }

    public static void setSides(Person user) {
        ArrayList<Person> rootDad = new ArrayList<>();
        ArrayList<Person> rootMom = new ArrayList<>();
        rootDad.add(findPerson(user.getFather()));
        mFatherSide.add(findPerson(user.getFather()));
        rootMom.add(findPerson(user.getMother()));
        mMotherSide.add(findPerson(user.getMother()));
        fatherSide(rootDad);
        motherSide(rootMom);
    }

    private static void fatherSide(ArrayList<Person> persons) {
        ArrayList<Person> temp = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            Person child = persons.get(i);
            if(child.getFather() == null) {
                return;
            }
            temp.add(findPerson(child.getFather()));
            mFatherSide.add(findPerson(child.getFather()));
            temp.add(findPerson(child.getMother()));
            mFatherSide.add(findPerson(child.getMother()));
        }
        fatherSide(temp);
    }

    private static void motherSide(ArrayList<Person> persons) {
        ArrayList<Person> temp = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            Person child = persons.get(i);
            if(child.getFather() == null) {
                return;
            }
            temp.add(findPerson(child.getFather()));
            mMotherSide.add(findPerson(child.getFather()));
            temp.add(findPerson(child.getMother()));
            mMotherSide.add(findPerson(child.getMother()));
        }
        motherSide(temp);
    }

    public static boolean filter(Event e) {
        if (!mFilterFemaleEvents) {
            if (findPerson(e.getPersonID()).getGender().equals("f")) {
                return false;
            }
        }
        if (!mFilterMaleEvents) {
            if (findPerson(e.getPersonID()).getGender().equals("m")) {
                return false;
            }
        }
        if (!mFilterMotherSide) {
            for (int i = 0; i < Model.mMotherSide.size(); i++) {
                if (mMotherSide.get(i).getPersonID().equals(e.getPersonID())) {
                    return false;
                }
            }
        }
        if (!mFilterFatherSide) {
            for (int i = 0; i < Model.mFatherSide.size(); i++) {
                if (mFatherSide.get(i).getPersonID().equals(e.getPersonID())) {
                    return false;
                }
            }
        }
        else {
            if(filteredEvents.size() == 0 || filteredEvents == null) {
                return false;
            } else {
                for(int i = 0; i < filteredEvents.size(); i++) {
                    if(filteredEvents.get(i).equals(e.getEventType().toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

}
