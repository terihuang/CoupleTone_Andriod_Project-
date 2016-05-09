// Team Number: 31
// Project: CoupleTones MileStone
// FileName: LocationItem.java
// Description: class to create a Location Object, store name, latitude and logtitude
//              of a favorite location


package com.example.khor_000.testapp;

public class LocationItem {
    private  String locName;
    private double latitude;
    private double longitude;

    public LocationItem(){
        super();
    }

    public LocationItem(String name, double lat, double lng){
        super();
        this.locName = name;
        this.latitude = lat;
        this.longitude = lng;
    }

    public String getName() { return locName; }
    public double getLatitude() { return latitude; }
    public double getLongitude(){ return longitude; }

}
