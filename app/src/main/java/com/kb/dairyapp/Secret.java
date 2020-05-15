package com.kb.dairyapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Secret extends Fragment {

    ImageView img;
    ListView itemsListViewSecret;
    public static ArrayList<EventDetail> secretlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TinyDB tinydb = new TinyDB(getContext());
        if(tinydb.getListEvent("player").size()>0)
            secretlist= tinydb.getListEvent("player");
        else
            secretlist=new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_secret, container, false);
        img = view.findViewById(R.id.img);
        itemsListViewSecret = (ListView)view.findViewById(R.id.list_view_items);
        final CustomListAdapter adapter = new CustomListAdapter(getContext(),
                generateItemsList());
        itemsListViewSecret.setAdapter(adapter);
        itemsListViewSecret.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                secretlist.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Secret Deleted...",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        return view;
    }
    public void openDialog(){
        ExampleDialog1 eg = new ExampleDialog1();
        eg.show(getFragmentManager(),"example dialog1");
    }
    private ArrayList<EventDetail> generateItemsList() {
        return secretlist;
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
        tinydb.putListEvent("player",secretlist);
        super.onDestroy();
    }

    @Override
    public void onStop() {
        //TinyDB tinydb = new TinyDB(getContext());
        //tinydb.putListEvent("players",eventlist);
        super.onStop();
    }
}
