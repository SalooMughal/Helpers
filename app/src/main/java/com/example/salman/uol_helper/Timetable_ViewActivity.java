package com.example.salman.uol_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Timetable_ViewActivity extends AppCompatActivity implements Timetable_async.Onstart{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__view);
        listView = (ListView) findViewById(R.id.listview);
        Timetable_async custom_asynctask3= new Timetable_async(Timetable_ViewActivity.this);
        custom_asynctask3.execute();
    }

    @Override
    public void getstart(ArrayList<Timetable> list) {
        int layoutId = R.layout.timetable_adapter;
        Timetable_Adapter adapter = new Timetable_Adapter(this, layoutId, list);
        listView.setAdapter(adapter);
    }
}
