package com.example.khor_000.testapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Emily on 5/7/2016.
 */
public class SMSNotification extends Activity{
    protected void sendSMS(){
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        //instead of new string, get phone number of linked partner
        smsIntent.putExtra("address", new String("0123456789"));
        smsIntent.putExtra("sms_body", "Your partner just visited a favorite location!");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...","");
        }
        catch (android.content.ActivityNotFoundException ex){
        }
    }
}
