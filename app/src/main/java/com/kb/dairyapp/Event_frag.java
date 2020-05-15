package com.kb.dairyapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.ArrayList;



public class Event_frag extends Fragment {
    ImageView img;
    ListView itemsListView;
    public static  ArrayList<EventDetail> eventlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TinyDB tinydb = new TinyDB(getContext());
        if(tinydb.getListEvent("players").size()>0)
            eventlist = tinydb.getListEvent("players");
        else
            eventlist=new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_event_frag, container, false);
        img = view.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        itemsListView = (ListView)view.findViewById(R.id.list_view_items);
        final CustomListAdapter adapter = new CustomListAdapter(getContext(),
                generateItemsList());
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                eventlist.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Event Deleted...",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return view;
        }
        public void openDialog(){
        ExampleDialog eg = new ExampleDialog();
        eg.show(getFragmentManager(),"example dialog");
        }
    private ArrayList<EventDetail> generateItemsList() {
        return eventlist;
    }

    @Override
    public void onStart() {
        //TinyDB tinydb = new TinyDB(getContext());
        //eventlist = tinydb.getListEvent("players");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        TinyDB tinydb = new TinyDB(getContext());
        tinydb.putListEvent("players",eventlist);
        super.onDestroy();
    }

    @Override
    public void onStop() {
        //TinyDB tinydb = new TinyDB(getContext());
        //tinydb.putListEvent("players",eventlist);
        super.onStop();
    }
}
