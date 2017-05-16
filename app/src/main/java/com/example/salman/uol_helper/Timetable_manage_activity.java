package com.example.salman.uol_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Timetable_manage_activity extends AppCompatActivity implements Timetable_Manage_Async.Onstart {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__view);
        listView = (ListView) findViewById(R.id.listview);
        Timetable_Manage_Async custom_asynctask4 = new Timetable_Manage_Async(Timetable_manage_activity.this);
        custom_asynctask4.execute();
    }

    @Override
    public void getstart(final ArrayList<Timetable> list) {
        int layoutId = R.layout.timetable_adapter;
        Timetable_Adapter adapter = new Timetable_Adapter(this, layoutId, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Timetable timetable=list.get(position);

                Intent intent=new Intent(getApplicationContext(),Add_StatusActivity.class);
                intent.putExtra("id",timetable.getId());
                startActivity(intent);
            }
        });
    }
}
