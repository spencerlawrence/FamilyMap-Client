package com.example.spencer.familymap.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.spencer.familymap.Activities.MapActivity;
import com.example.spencer.familymap.Activities.PersonActivity;
import com.example.spencer.familymap.R;

import java.util.List;

public class MyExpandableAdapter extends ExpandableRecyclerAdapter<MyParentViewHolder, MyChildViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;

    public MyExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_parent, viewGroup, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_child, viewGroup, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int i, Object o) {
        RecyclerViewParent parent = (RecyclerViewParent) o;
        parentViewHolder.mTitle.setText(parent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder childViewHolder, int i, Object o) {
        RecyclerViewChild child = (RecyclerViewChild) o;
        childViewHolder.mIcon.setText(child.getIcon());
        childViewHolder.mInfo.setText(child.getInfo());
        childViewHolder.setType(child.getType());
        childViewHolder.setID(child.getID());

        childViewHolder.setOnClickListener(new IRecyclerViewCallBack() {
                    @Override
                    public void onChildListItemClick(String type, String ID) {
                        if (type.equals("event")) {
                            Intent intent = new Intent(mContext, MapActivity.class);
                            intent.putExtra("selected", ID);
                            mContext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, PersonActivity.class);
                            intent.putExtra("selected", ID);
                            mContext.startActivity(intent);
                        }
                    }
                });

    }
}
