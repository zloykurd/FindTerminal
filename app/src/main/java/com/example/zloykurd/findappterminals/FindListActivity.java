package com.example.zloykurd.findappterminals;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FindListActivity extends AppCompatActivity {
    TextView tw;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    ListAdapter adapter = new ListAdapter(FindListActivity.this, getData(), FindListActivity.this.getApplicationContext());
    ListView lvNotes = (ListView) findViewById(R.id.listView);
    lvNotes.setAdapter(adapter);
    getSupportActionBar().setTitle("Терминалы (" + String.valueOf(adapter.getCount()) + ")");
    getSupportActionBar().setSubtitle("");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public ArrayList<Point> getData() {

        Database db = new Database(FindListActivity.this.getApplicationContext());
        final ArrayList<Point> stringItems = new ArrayList<Point>();

        ArrayList<Point> pr = (ArrayList<Point>) db.getAllMapsPoint();

        for (Point p : pr) {
            stringItems.add(p);
        }

        return stringItems;

    }
}
