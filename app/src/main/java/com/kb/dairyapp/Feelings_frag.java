package com.kb.dairyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Feelings_frag extends Fragment {

    ImageView img;
    ListView itemsListView;
    public static ArrayList<EventDetail> feelinglist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TinyDB tinydb = new TinyDB(getContext());
        if(tinydb.getListEvent("player2").size()>0)
            feelinglist = tinydb.getListEvent("player2");
        else
            feelinglist=new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_feelings, container, false);
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
                feelinglist.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Event Deleted...",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        return view;
    }
    public void openDialog(){
        ExampleDialog2 eg = new ExampleDialog2();
        assert getFragmentManager() != null;
        eg.show(getFragmentManager(),"example dialog2");
    }
    private ArrayList<EventDetail> generateItemsList() {
        return feelinglist;
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
        tinydb.putListEvent("player2",feelinglist);
        super.onDestroy();
    }

    @Override
    public void onStop() {
        //TinyDB tinydb = new TinyDB(getContext());
        //tinydb.putListEvent("players",eventlist);
        super.onStop();
    }

}


