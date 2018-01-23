package com.example.spencer.familymap.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.spencer.familymap.R;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by Spencer on 8/12/17.
 */

public class MyChildViewHolder extends ChildViewHolder {



    public IconTextView mIcon;
    public TextView mInfo;

    private String mType;
    private String mID;

    public MyChildViewHolder(View itemView) {
        super(itemView);
        mIcon = itemView.findViewById(R.id.icon);
        mInfo = itemView.findViewById(R.id.childInfo);
    }

    public void setOnClickListener(final IRecyclerViewCallBack listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onChildListItemClick(mType, mID);
            }
        });
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public void setID(String mID) {
        this.mID = mID;
    }
}
