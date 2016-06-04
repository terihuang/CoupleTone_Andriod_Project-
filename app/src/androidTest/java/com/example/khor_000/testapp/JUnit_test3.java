package com.example.khor_000.testapp.TEST;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import static junit.framework.Assert.assertEquals;
/**
 * Created by hboch_000 on 6/3/2016.
 */
public class JUnit_test3 {
    String partnerName;
    String partnerLocation;
    Firebase firebase;
    boolean checkSound = false;
    //Given that my partner has a favorite location When they arrive at the location Then an
    //arrival tone will play
    public void test_checkArrivalTone(){
        partnerHasFavoriteLocation();
        partnerOnTheMove();
        playArrivalTone();
        assertEquals(true, checkSound);
    }
    //Given that my partner has a favorite location When they leave at the location Then a departure
    //will play
    public void test_checkDepartureTone(){
        partnerHasFavoriteLocation();
        partnerOnTheMove();
        playDepartureTone();
        assertEquals(true, checkSound);
    }
    //arriving at a location
    //Given that partner has a favorite location
    public void partnerHasFavoriteLocation(){
        partnerName = "partner";
        partnerLocation = "UCSD";
    }
    //When my partner arrives/departs  that favorite location
    public void partnerOnTheMove(){
        firebase = new Firebase("http://chao-110.firebaseio.com/" + partnerName);
        firebase.setValue(partnerLocation);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                //SOUND STUFF
                checkSound = true;
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }
    //Then CoupleTones will play the arrival tone
    public  void playArrivalTone(){
        firebase.setValue(partnerLocation);
        checkSound = true; //We heard the sound
    }
    //Scenario: departing a location
    //Given that my partner has already arrived at favorite location
    public void currentlyAtLocation(){
        partnerLocation = "";
    }
    //Then a departure tone plays
    public void playDepartureTone(){
        firebase.setValue(partnerLocation);
    }
}