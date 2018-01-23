package com.example.spencer.familymap.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.spencer.familymap.R;

/**
 * Created by Spencer on 8/12/17.
 */

public class MyParentViewHolder extends ParentViewHolder {

    public TextView mTitle;
    public ImageButton mArrow;

    public MyParentViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.parentTitle);
        mArrow = (ImageButton) itemView.findViewById(R.id.expandArrow);
    }
}
