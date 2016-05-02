package com.example.khor_000.testapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> arrayList;
    private EditText favName;
    private ArrayAdapter<String>arrayAdapter;
    private Button showList;
    private LinearLayout setLocation;
    private LinearLayout locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setLocation = (LinearLayout) getLayoutInflater().inflate(R.layout.set_location,null);
        locationList = (LinearLayout) getLayoutInflater().inflate(R.layout.location_list,null);
        ListView listView = (ListView) locationList.findViewById(R.id.list_view);
        showList = (Button) findViewById(R.id.listButton);
        String [] items = {"Aasfsdffgggrttrhyt","Bsfdrtgrtfsdg4yj65545","C123244rrefdsvbtyj5yhtrjuun6ryrth"};
        arrayList = new ArrayList<String>(Arrays.asList(items));
        arrayAdapter = new ArrayAdapter<String>(listView.getContext(), R.layout.each_location, arrayList);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
   //     listView.setTextFilterEnabled(true);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        final Marker addFavor = mMap.addMarker(new MarkerOptions().position(sydney).title("where am I  "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the list ( change page)
                Intent locationListPage = new Intent(MapsActivity.this, ListOfFavLocations.class);
                MapsActivity.this.startActivity(locationListPage);
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {

                favName = (EditText) setLocation.findViewById(R.id.nameOfLocation);
                //  show the alert box
                addFavor.setPosition(point);
                View setLocat = LayoutInflater.from(MapsActivity.this).inflate(R.layout.set_location,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                builder.setMessage("What is the favorite location name?");
                builder.setView(setLocat).setPositiveButton("add",new DialogInterface.OnClickListener(){

                   public void onClick(DialogInterface dialog, int which){

                       // set it to Data - base
                       String locationName = favName.getText().toString();
                       if (TextUtils.isEmpty(locationName)) {
                           arrayList.add(locationName);
                           arrayAdapter.notifyDataSetChanged();
                           Toast.makeText(getApplicationContext(),"added favorite location",Toast.LENGTH_SHORT).show();
                       }

                   }                    // close the prompt after click cancel
                }).setNegativeButton("Cancel",null).setCancelable(true);  // cancelable even using back key

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        LocationListener locationListener  = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED  ){

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 100);

            Log.d("test1","ins");
            return;

        }else if (mMap != null){
            Log.d("test2", "outs");
            mMap.setMyLocationEnabled(true);
        }

        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }
}