package com.kb.dairyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    ImageView imgbtn,imgsetting;
    Button bt1 ,bt2,bt3;
    TextView selectdate;
    private static final int PERMISSION_STORAGE = 1000;
    private final String CHANNELID="Personal Information";
    private  final int  NotificationID=001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.child_container,new Event_frag()).commit();
        bt1=findViewById(R.id.event_button);
        bt2=findViewById(R.id.feeling_button);
        bt3=findViewById(R.id.secret_button);
        imgbtn=findViewById(R.id.calender_button);
        imgsetting=findViewById(R.id.setting);
        selectdate=findViewById(R.id.select_date);
        final View child = findViewById(R.id.child_container);
        final Button[] temp = {null};
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child.setBackground(getResources().getDrawable(R.drawable.custom_back));
                bt3.setBackground(getResources().getDrawable(R.drawable.custom_button));
                bt2.setBackground(getResources().getDrawable(R.drawable.custom_button));
                bt1.setBackground(getResources().getDrawable(R.drawable.change_button));
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.child_container,new Event_frag()).commit();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackground(getResources().getDrawable(R.drawable.custom_button));
                bt3.setBackground(getResources().getDrawable(R.drawable.custom_button));
                bt2.setBackground(getResources().getDrawable(R.drawable.change_button));
                child.setBackground(getResources().getDrawable(R.drawable.custom_back2));
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.child_container,new Feelings_frag()).commit();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt1.setBackground(getResources().getDrawable(R.drawable.custom_button));
               bt2.setBackground(getResources().getDrawable(R.drawable.custom_button));
                bt3.setBackground(getResources().getDrawable(R.drawable.change_button));
                child.setBackground(getResources().getDrawable(R.drawable.custom_back3));
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.child_container,new Secret()).commit();

            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dt = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        selectdate.setText(date);
                    }
                }, year, month, day);
                dt.show();
            }
        });
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createnotificationchannel();
                NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(),CHANNELID);
                builder.setSmallIcon(R.drawable.diary_logo);
                builder.setContentTitle("DIARY Notification");
                builder.setContentText(" Where you Like to write Something in Diary ?");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(NotificationID,builder.build());
            }
            private  void createnotificationchannel() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "personal notification";
                    String description = "Include all the personal notifications";
                    int importancce = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel notificationChannel = new NotificationChannel(CHANNELID, name, importancce);
                    notificationChannel.setDescription(description);
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
