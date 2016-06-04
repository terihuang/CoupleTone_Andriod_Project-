package com.example.khor_000.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Khor_000 on 30/5/2016.
 */
public class DailyLocList extends Activity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> toneAdapter;
    private ArrayAdapter<String> vibAdapter;

    private String locName;
    private List<String> myLocationsName;
    private ListView toneView;
    private ListView vibView;
    private TextView infoText;
    private String[] toneAry= {"Default Tone","Tone1","Tone2","Tone3","Tone4","Tone5"};
    private String[] vibAry= {"Default Vibration","Vibration1","Vibration2","Vibration3","Vibration4","Vibration5"};

    private long[][]vibraPatterns = { {0,200,500}, {0,500,200}, {0, 1000, 500},
            {0,200,1000}, {0, 500, 1000} } ;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_daily_loc_list);
        listView = (ListView) findViewById(R.id.histlist);
        myLocationsName =  new ArrayList<String>();
        SharedPreferences sp = getSharedPreferences("LocatHistData", MODE_PRIVATE);
        int locNum = sp.getInt("locNum", 0);
        for (int i = locNum; i >= 1; i--) {
            String locVisited = sp.getString("locName" + i, "");
            myLocationsName.add(locVisited);
        }

        toneView = (ListView) findViewById(R.id.toneList);
        vibView = (ListView) findViewById(R.id.vibList);
        infoText = (TextView) findViewById(R.id.info);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myLocationsName);
        listView.setAdapter(arrayAdapter);

        toneAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toneAry);
        toneView.setAdapter(toneAdapter);

        vibAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vibAry);
        vibView.setAdapter(vibAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id ) {

                View setView = LayoutInflater.from(DailyLocList.this).inflate(R.layout.modtype, null);
                android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(DailyLocList.this); //Read Update

                locName = ((TextView)viewClicked).getText().toString();
                String[] parts = locName.split("\t",2);
                locName = parts[0];
                Toast.makeText(getApplicationContext(), locName, Toast.LENGTH_SHORT).show();



                alertDialog.setTitle("Choose what to change: ");
                alertDialog.setView(setView).setPositiveButton("   Tone                       ", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        listView.setVisibility(View.GONE);
                        toneView.setVisibility(View.VISIBLE);
                        infoText.setVisibility(View.GONE);


                        toneView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                                    int position, long id ) {

                                SharedPreferences tone = getSharedPreferences( "tone", MODE_PRIVATE);
                                SharedPreferences.Editor toneEditor = tone.edit();
                                toneEditor.putInt(locName,position);
                                toneEditor.apply();

                                //playTone(position+1);

                            }


                        });








                    }                    // close the prompt after click cancel
                }).setNegativeButton("  Vibration                ", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        listView.setVisibility(View.GONE);
                        vibView.setVisibility(View.VISIBLE);
                        infoText.setVisibility(View.GONE);






                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                                    int position, long id ) {

                                SharedPreferences vib = getSharedPreferences( "vib", MODE_PRIVATE);
                                SharedPreferences.Editor vibEditor = vib.edit();
                                vibEditor.putInt(locName,position);
                                vibEditor.apply();

                                //playVibrate(vibraPatterns[position],1);

                            }

                        });

                    }                    // close the prompt after click cancel
                }).setCancelable(true);  // cancelable even using back key

                android.app.AlertDialog alert = alertDialog.create();
                alert.show();
            }


        });



     }


    // method to play vibration
    public void playVibrate( long[] pattern, int repeat ){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        for(int i = 0; i < repeat; i++ ) {
            v.vibrate(pattern, -1);
        }

    }

    // method to play sound notification
    public void playTone( int index ) {
        Uri notification = RingtoneManager.getDefaultUri(index);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                r.stop();
            }
        };

        long ringDelay = 3000;
        Timer timer = new Timer();
        timer.schedule(task, ringDelay);
    }

}

