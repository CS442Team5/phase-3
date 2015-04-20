package com.example.filipenotebook.calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by FilipeNotebook on 19/04/2015.
 */
public class SoundWakefulReceiver extends BroadcastReceiver {

    private String option;
    @Override
    public void onReceive(Context context, Intent intent) {
        option = intent.getStringExtra("option");
        Log.d("receiver opt", option);
        if (option.equals("off")) {
            Intent service = new Intent(context, SoundService.class);
            Log.d("receiver", "receiver off !!");
            context.startService(service);
        } else{
            Intent service = new Intent(context, SoundServiceOn.class);
            Log.d("receiver", "receiver on !!");
            context.startService(service);
        }


        //startWakefulService(context, service);
    }
}
