package com.kb.dairyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import static com.kb.dairyapp.Event_frag.eventlist;
import static com.kb.dairyapp.Secret.secretlist;

public class ExampleDialog1 extends AppCompatDialogFragment {
    DatePickerDialog.OnDateSetListener setListener;
    EditText txtdate,txtevent,txttime ;
    ImageView img1,img2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        View v = inf.inflate(R.layout.layout_dialog,null);
        txtdate=v.findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(calendar.HOUR_OF_DAY);
        final int min = calendar.get(calendar.MINUTE);
        img1=v.findViewById(R.id.date_img_id);
        img2=v.findViewById(R.id.clock_img_id);
        txtdate = v.findViewById(R.id.date);
        txtevent=v.findViewById(R.id.event);
        txttime=v.findViewById(R.id.time);
        final FragmentActivity fragmentActivity = getActivity();
        Log.d("message",fragmentActivity.getComponentName().toString());
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dt = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        txtdate.setText(date);
                    }
                }, year, month, day);
                dt.show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker View, int hour, int min) {
                        txttime.setText(hour+":"+min);
                    }
                },hour,min,android.text.format.DateFormat.is24HourFormat(getActivity()));
                timePickerDialog.show();
            }
        });



        build.setView(v)
                .setTitle("DIARY")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Cancelled",Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String datetxt = txtdate.getText().toString();
                        String eventtxt = txtevent.getText().toString();
                        String timetxt = txttime.getText().toString();
                        secretlist.add(new EventDetail(datetxt,eventtxt,timetxt));
                    }
                });
        txtevent.setHint("Enter a Secret...");
        return  build.create();
    }
}
