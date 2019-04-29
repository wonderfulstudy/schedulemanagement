package com.example.schedulemanagement;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.TabHost;

import java.util.Calendar;

public class Calender extends AppCompatActivity {

    int year,month,day;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        TabHost tabHost=findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater=LayoutInflater.from(this);
        inflater.inflate(R.layout.tab01,tabHost.getTabContentView());
        inflater.inflate(R.layout.tab02,tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("日程详情").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("群组").setContent(R.id.right));

        datePicker=findViewById(R.id.datePicker);
        Calendar calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calender.this.year=year;
                Calender.this.month=monthOfYear;
                Calender.this.day=dayOfMonth;
            }
        });
    }
}
