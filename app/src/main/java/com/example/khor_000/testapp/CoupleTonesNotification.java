package com.example.khor_000.testapp;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/**
 * Created by Emily on 6/3/2016.
 */
public class CoupleTonesNotification extends AppCompatActivity{
    private Context context;
    public CoupleTonesNotification(Context context) {
        this.context = context;
    }
    public void sendNotification(NotificationManager manager, String locName){
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("CoupleTones")
                .setContentText("Your partner just visited " + locName)
                .build();
        manager.notify(0,notification);

    }
}