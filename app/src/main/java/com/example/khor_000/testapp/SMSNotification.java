package com.example.khor_000.testapp;

import android.app.Activity;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Emily on 5/7/2016.
 */
public class SMSNotification extends Activity{
    public void sendSMS(){
        String message = "Your partner just visited a favorite location!";
        String phoneNo = new String("5108624681");
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
