package com.example.khor_000.testapp;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import com.example.khor_000.testapp.LocationItem;
import com.example.khor_000.testapp.MapsActivity;
import com.example.khor_000.testapp.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Created by hboch_000 on 5/8/2016.
 */
public class JUnit_test extends ActivityInstrumentationTestCase2<MapsActivity> {
    MapsActivity mapsActivity = new MapsActivity();
    public JUnit_test() {
        super(MapsActivity.class);
    }
    public void selectOneLocation(){
        LocationItem loc_test = new LocationItem("UCSD", 300, 300);
    }
    public void test_Establish_Location(){
        mapsActivity = getActivity();
        TextView textView = (TextView) mapsActivity.findViewById(R.id.locName);
        String tester = textView.getText().toString();
        assertEquals("UCSD", tester);
    }
}