package com.kb.dairyapp;

import android.widget.EditText;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDetail {
    String date;
    String Message;
    String time;

    public EventDetail(String datetxt, String eventtxt, String timetxt) {
        this.date = datetxt;
        this.Message = eventtxt;
        this.time = timetxt;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return Message;
    }

    public String getTime() {
        return time;
    }
}
