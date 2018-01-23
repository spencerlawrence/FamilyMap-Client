package com.example.spencer.familymap.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class RecyclerViewParent implements ParentObject{

    private List<Object> mChildrenList;
    private String mTitle;

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
