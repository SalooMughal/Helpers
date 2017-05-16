package com.example.salman.uol_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Add_StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__status);
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Button button = (Button) findViewById(R.id.button);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

//         Spinner Drop down elements

        List<String> status = new ArrayList<String>();
        status.add("Held");
        status.add("Notheld");
        status.add("Late");


        // Creating adapter for spinner

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
//         Drop down layout style - list view with radio button

        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(statusAdapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                String tstatus = spinner1.getSelectedItem().toString();
                Add_Status_Async add_status_async = new Add_Status_Async(Add_StatusActivity.this);
                add_status_async.execute(id,tstatus);

            }
        });

    }
}
