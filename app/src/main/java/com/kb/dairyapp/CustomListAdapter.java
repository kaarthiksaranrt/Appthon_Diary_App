package com.kb.dairyapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<EventDetail> items; //data source of the list adapter
    //public constructor
    public CustomListAdapter(Context context, ArrayList<EventDetail> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_list_view, parent,
                            false);
        }
        EventDetail currentItem = (EventDetail) getItem(position);
        TextView textViewDate = (TextView)
                convertView.findViewById(R.id.date_id);
        TextView textViewtime = (TextView)
                convertView.findViewById(R.id.time_id);
        TextView textViewmessage = (TextView)
                convertView.findViewById(R.id.message_id);
        textViewDate.setText(
                currentItem.getDate());
        textViewtime.setText(
                currentItem.getTime());
        textViewmessage.setText(
                currentItem.getMessage());
        return convertView;
    }
}
