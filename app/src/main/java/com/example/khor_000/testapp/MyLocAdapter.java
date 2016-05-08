package com.example.khor_000.testapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khor_000.testapp.LocationItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Emily on 5/8/2016.
 */
public class MyLocAdapter extends ArrayList<LocationItem> {
    public void MyLocAdapter(){}
/*
    public void MyLocAdapter() {
        super(MapsActivity.this, R.layout.single_location, myLocations);
    }

    public void add(LocationItem newLoc) {
        super.add(newLoc);
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //make sure we have view if null
        View itemView = convertView;
        if (itemView == null) {
            itemView = getLayoutInflater().inflate(R.layout.single_location, parent, false);
        }

        //find the location to work with
        LocationItem currLoc = myLocations.get(position);

        //fill the view and location name
        TextView makeText = (TextView) itemView.findViewById(R.id.locName);
        makeText.setText(currLoc.getName());

        //latitude
        TextView laText = (TextView) itemView.findViewById(R.id.latTxt);
        Double latVal = currLoc.getLatitude();
        latVal = Double.parseDouble(new DecimalFormat("##.##").format(latVal));
        laText.setText("LAT: "+ latVal + "\t");

        //longtitude
        TextView lngText = (TextView) itemView.findViewById(R.id.lngTxt);
        Double lngVal = currLoc.getLatitude();
        lngVal = Double.parseDouble(new DecimalFormat("##.##").format(lngVal));
        laText.setText("LON: " + lngVal);

        return itemView;
    }
    */
}
