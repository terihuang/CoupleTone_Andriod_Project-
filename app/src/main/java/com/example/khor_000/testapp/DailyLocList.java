package com.example.khor_000.testapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khor_000 on 30/5/2016.
 */
public class DailyLocList extends Activity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> myLocationsName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_daily_loc_list);
        listView = (ListView) findViewById(R.id.histlist);
        myLocationsName =  new ArrayList<String>();
        SharedPreferences sp = getSharedPreferences("LocatHistData", MODE_PRIVATE);
        int locNum = sp.getInt("locNum", 0);
        for (int i = locNum; i >= 1; i--) {
            String locVisited = sp.getString("locName" + i, "");
            myLocationsName.add(locVisited);
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myLocationsName);
        listView.setAdapter(arrayAdapter);

     }

}
