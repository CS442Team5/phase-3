package com.example.filipenotebook.calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.HandlerThread;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ManualSelectedEvent extends ActionBarActivity {
    private ArrayList days;
    String eventName;
    int hourStart;
    int minuteStart;
    int hourEnd;
    int minuteEnd;
    boolean repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioManager amanager;




        setContentView(R.layout.activity_manual_selected_event);
        TextView tv1 = (TextView)findViewById(R.id.selectedEventManual);
        eventName = getIntent().getStringExtra("eventName");
        hourStart = getIntent().getIntExtra("hourStart", 0);
        hourStart = Integer.parseInt(pad(hourStart));
        minuteStart = getIntent().getIntExtra("minuteStart",0);
        minuteStart = Integer.parseInt(pad(minuteStart));
        hourEnd = getIntent().getIntExtra("hourEnd",0);
        hourEnd = Integer.parseInt(pad(hourEnd));
        minuteEnd = getIntent().getIntExtra("minuteEnd",0);
        minuteEnd = Integer.parseInt(pad(minuteEnd));
        repeat = getIntent().getBooleanExtra("repeat",false);
        alarm(hourStart, minuteStart, "off");
        alarm(hourEnd, minuteEnd, "on");
        tv1.setText("Event name: " + eventName + " starting at: " + pad(hourStart) +":" + pad(minuteStart)+ " and ending at: "+ pad(hourEnd) +":" +pad(minuteEnd) );
        if (repeat == true)
            tv1.append(" and will repeat");
        days();
    }
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
    public void alarm(int hour, int minute, String option){

        Calendar cur_cal = new GregorianCalendar();
        cur_cal.setTimeInMillis(System.currentTimeMillis());//set the current time and date for this calendar
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        Log.d("calendartimeValue", String.valueOf(cal.getTimeInMillis()));
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, cur_cal.get(Calendar.MILLISECOND));
        cal.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
        cal.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));

        Intent intent = new Intent(this, SoundWakefulReceiver.class);
        intent.putExtra("option", option);
        Log.d("option:", option);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pintent = PendingIntent.getBroadcast(this, _id, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pintent);

    }
    public void days(){
        boolean sunday = getIntent().getBooleanExtra("sunday",false);
        boolean monday = getIntent().getBooleanExtra("monday",false);
        boolean tuesday = getIntent().getBooleanExtra("tuesday",false);
        boolean wednesday = getIntent().getBooleanExtra("wednesday",false);
        boolean thursday = getIntent().getBooleanExtra("thursday",false);
        boolean friday = getIntent().getBooleanExtra("friday",false);
        boolean saturday = getIntent().getBooleanExtra("saturday",false);

        TextView tv2 = (TextView)findViewById(R.id.daysManual);
        if (sunday == true)
            tv2.append("Sunday ");
        if (monday == true)
            tv2.append("Monday ");
        if (tuesday == true)
            tv2.append("Tuesday ");
        if (wednesday == true)
            tv2.append("Wednesday ");
        if (thursday == true)
            tv2.append("Thursday ");
        if (friday == true)
            tv2.append("Friday ");
        if (saturday == true)
            tv2.append("Saturday ");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manual_selected_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
