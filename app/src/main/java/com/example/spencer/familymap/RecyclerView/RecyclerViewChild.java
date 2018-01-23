package com.example.spencer.familymap.RecyclerView;

public class RecyclerViewChild {
    private String mIcon;
    private String mInfo;
    private String mType;
    private String mID;

    public RecyclerViewChild(String icon, String info, String type, String ID) {
        mIcon = icon;
        mInfo = info;
        mType = type;
        mID = ID;
    }

    public String getInfo() {
        return mInfo;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getType() {
        return mType;
    }

    public String getID() {
        return mID;
    }
}
