package com.example.khor_000.testapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Khor_000 on 28/4/2016.
 */
public class ListOfFavLocations extends Activity {

    static ArrayAdapter<String> arrayAdapter;
    static ArrayList<String> arrayList;
    static ListView listView;

    /*
    public ListOfFavLocations(){

    }
    */

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);
        listView = (ListView) findViewById(R.id.list_view);
        arrayList = new ArrayList<String>();
        arrayList.add("AAAA");
        arrayList.add("AbbA");
        arrayList.add("ACCA");
        arrayList.add("ADDD");
        arrayAdapter = new ArrayAdapter<String>(ListOfFavLocations.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        Runnable run = new Runnable() {
            public void run() {
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        };
        //arrayAdapter.notifyDataSetChanged();
    }

    public static void addItem(String item){

        arrayList.add(item);

        arrayAdapter.notifyDataSetChanged();

    }
    public void add(){
        arrayList.add("A");
        arrayList.add("b");
        arrayList.add("C");
        arrayList.add("D");
        arrayAdapter.notifyDataSetChanged();
    }
    public void addItem2(String item){

        arrayList.add(item);

        arrayAdapter.notifyDataSetChanged();

    }

    public static void addItem2(){
        arrayList.add("A");
        arrayList.add("V");
        arrayList.add("C");
        arrayList.add("D");
        arrayAdapter.notifyDataSetChanged();

    }

 //   public void add(String item){}
}
