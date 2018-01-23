package com.example.spencer.familymap.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spencer.familymap.Activities.MapActivity;
import com.example.spencer.familymap.Activities.PersonActivity;
import com.example.spencer.familymap.R;

import java.util.ArrayList;

/**
 * Created by Spencer on 8/14/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyChildViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<RecyclerViewChild> mDataset;

    public MyAdapter(Context context, ArrayList<RecyclerViewChild> myDataset) {
        mDataset = myDataset;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public MyChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_child, parent, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyChildViewHolder holder, int position) {
        RecyclerViewChild child = mDataset.get(position);
        holder.mIcon.setText(child.getIcon());
        holder.mInfo.setText(child.getInfo());
        holder.setType(child.getType());
        holder.setID(child.getID());

        holder.setOnClickListener(new IRecyclerViewCallBack() {
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

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setFilter(ArrayList<RecyclerViewChild> newList) {
        mDataset = new ArrayList<>();
        mDataset.addAll(newList);
        notifyDataSetChanged();
    }
}
