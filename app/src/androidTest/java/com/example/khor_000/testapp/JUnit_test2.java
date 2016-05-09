package com.example.khor_000.testapp;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import com.example.khor_000.testapp.LocationItem;
import com.example.khor_000.testapp.MapsActivity;
import android.location.Location;
//import org.junit.Test;
/**
 * Created by Caitlin on 5/8/2016.
 */
public class JUnit_test2 extends ActivityInstrumentationTestCase2<MapsActivity> {
    MapsActivity mapsActivity;
    LocationItem testLoc;
    LocationItem secondLoc;
    Location mockedLocation;
    boolean notificationSent;
    LocationItem mockedLocation1;
    public JUnit_test2() {
        super(MapsActivity.class);
    }
    //Given I have a favorite location
    public void haveFavoriteLocation(){
        testLoc = new LocationItem("Warren Lecture Hall", 32.8, -117.2);
        //mapsActivity.addToMyLocations(testLoc);
    }
    //When I move within 1/10th of a mile radius of a favorite location
    public void iMoveWithinRadiusOfFavLocation() {
        /** mockedLocation = new Location(mapsActivity.getUpdatedLo());
         mockedLocation.setLatitude( 33.0);
         mockedLocation.setLongitude(-116.9);**/
        mockedLocation1 = new LocationItem("iAmHere", 33.0,-116.9);
    }
    //then the app should send a notification
    public void shouldSendANotification(){
        //notificationSent = mapsActivity.inLocation(mapsActivity.getUpdatedLo(),testLoc);
        notificationSent = getRange(testLoc.getLatitude(), testLoc.getLongitude(), mockedLocation1.getLatitude(), mockedLocation1.getLongitude()) < 160.9;
    }
    //Given that I have two favorite locations
    public void haveTwoFavoriteLocations(){
        haveFavoriteLocation();
        secondLoc = new LocationItem("CSE Labs", 38.9,-125.0 );
        //mapsActivity.addToMyLocations(secondLoc);
    }
    //When I leave that favorite location
    public void iMoveOutofRadiusOfFavLocation(){
        mockedLocation1 = new LocationItem("newMyLocation",0.0,0.0);
        //mockedLocation.setLongitude(0.0);
        //mockedLocation.setLongitude(0.0);
    }
    public double getRange(double lat1, double lon1, double lat2, double lon2) {
        Double R = 6378.137; // Radius of earth in KM
        Double dLat = (lat2 - lat1) * Math.PI / 180;
        Double dLon = (lon2 - lon1) * Math.PI / 180;
        Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double d = R * c;
        d = d * 1000; // meters
        return d;
    }
    //@Test
    //Given that I have a favorite location
    //When I move within 1/10th of a mile radius of that location
    //Then the app should know to send a notification to my partner
    public void test_checkFirstScenario(){
        mapsActivity = getActivity();
        System.out.print("CHECKKKKKKK");
        haveFavoriteLocation();
        iMoveWithinRadiusOfFavLocation();
        shouldSendANotification();
        assertEquals(true, notificationSent);
    }
    //Given that I have a favorite location
    //And I just entered and left that favorite location
    //When I step within the radius of that fav location (not entered any other fav loc)
    //Then the app should not send a notification
    public void test_checkSecondScenario(){
        mapsActivity = getActivity();
        haveFavoriteLocation();
        iMoveWithinRadiusOfFavLocation(); //enter
        iMoveOutofRadiusOfFavLocation(); //exit
        iMoveWithinRadiusOfFavLocation(); //enter
        assertEquals(false, notificationSent);
    }
    /**
     //Given that I am near two favorite locations
     //When I move within 1/10th of a mile range of the two locations
     //THen the app should send a notification to my partner stating
     //that I just visited the closest visited the closer of the two
     public void testThirdScenario() {
     test_haveTwoFavoriteLocations();
     test_iMoveWithinRadiusOfFavLocation();
     assertEquals("Warren Lecture Hall",);
     }**/
    //Given that I am not near a favorite location
    //WHen I move to a non-favorite location
    //Then the app should not send a notification to my partner
/*
    public void test_FourthScenario(){
        mapsActivity = getActivity();
        haveFavoriteLocation();
        iMoveWithinRadiusOfFavLocation();
        shouldSendANotification();
        assertEquals(false, notificationSent);
    }
    */
}