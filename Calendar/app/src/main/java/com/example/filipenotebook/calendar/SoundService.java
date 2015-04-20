package com.example.filipenotebook.calendar;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

/**
 * Created by FilipeNotebook on 19/04/2015.
 */
public class SoundService extends IntentService {

    public SoundService() {
        super("Sound Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AudioManager amanager;
        Log.d("service","service started!!");
        amanager = (AudioManager)getSystemService(AUDIO_SERVICE);
        //amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        amanager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        //SoundWakefulReceiver.completeWakefulIntent(intent);
    }
}
