package com.example.filipenotebook.calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class ManualEvent extends ActionBarActivity {

    private TextView tvDisplayTime;
    private TextView tvDisplayTimeEnd;
    private TimePicker timePicker1;
    private Button btnChangeTime;
    private Button btnChangeTime2;

    private int hourStart;
    private int minuteStart;
    private int minuteEnd;
    private int hourEnd;

    static final int TIME_DIALOG_ID = 999;
    static final int END_ID = 998;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_event);

        setCurrentTimeOnView();
        setEndTime();
        addListenerOnButton();

    }

    // display current time
    public void setCurrentTimeOnView() {

        tvDisplayTime = (TextView) findViewById(R.id.tvTime);


        final Calendar c = Calendar.getInstance();
        hourStart = c.get(Calendar.HOUR_OF_DAY);
        minuteStart = c.get(Calendar.MINUTE);

        // set current time into textview
        tvDisplayTime.setText(
                new StringBuilder().append(pad(hourStart))
                        .append(":").append(pad(minuteStart)));

        // set current time into timepicker


    }
    public void setEndTime(){
        tvDisplayTimeEnd = (TextView) findViewById(R.id.tvTime2);


        final Calendar c = Calendar.getInstance();
        hourEnd = c.get(Calendar.HOUR_OF_DAY);
        minuteEnd = c.get(Calendar.MINUTE);
        hourEnd = hourEnd + 1;


        // set current time into textview
        tvDisplayTimeEnd.setText(
                new StringBuilder().append(pad(hourEnd))
                        .append(":").append(pad(minuteEnd)));
    }

    public void addListenerOnButton() {

        btnChangeTime = (Button) findViewById(R.id.btnChangeTime);
        btnChangeTime2 = (Button) findViewById(R.id.btnChangeTime2);
        btnChangeTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(END_ID);
            }
        });
        btnChangeTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hourStart, minuteStart,false);
            case END_ID:
                return new TimePickerDialog(this, timePickerListenerEnd, hourEnd, minuteEnd, false);
        }
        return null;
    }

    public void Next(View v){
        EditText event = (EditText) findViewById(R.id.eventManualName);

        String eventName = event.getText().toString();
        Intent intent = new Intent(this, ManualSelectedEvent.class);
        CheckBox repeat = (CheckBox) findViewById(R.id.repeatManual);
        boolean repeated = repeat.isChecked();
        CheckBox SundayC = (CheckBox) findViewById(R.id.sunday);
        boolean sunday = SundayC.isChecked();
        CheckBox mondayC = (CheckBox) findViewById(R.id.monday);
        boolean monday = mondayC.isChecked();
        CheckBox tuesdayC = (CheckBox) findViewById(R.id.tuesday);
        boolean tuesday = tuesdayC.isChecked();
        CheckBox wednesdayC = (CheckBox) findViewById(R.id.wednesday);
        boolean wednesday = wednesdayC.isChecked();
        CheckBox thursdayC = (CheckBox) findViewById(R.id.thursday);
        boolean thursday = thursdayC.isChecked();
        CheckBox fridayC = (CheckBox) findViewById(R.id.friday);
        boolean friday = fridayC.isChecked();
        CheckBox saturdayC = (CheckBox) findViewById(R.id.saturday);
        boolean saturday = saturdayC.isChecked();
        intent.putExtra("eventName", eventName);
        intent.putExtra("hourStart", hourStart);
        intent.putExtra("minuteStart", minuteStart);
        intent.putExtra("hourEnd", hourEnd);
        intent.putExtra("minuteEnd", minuteEnd);
        intent.putExtra("repeat", repeated);
        intent.putExtra("sunday", sunday);
        intent.putExtra("monday", monday);
        intent.putExtra("tuesday", tuesday);
        intent.putExtra("wednesday", wednesday);
        intent.putExtra("thursday", thursday);
        intent.putExtra("friday", friday);
        intent.putExtra("saturday", saturday);
        startActivity(intent);
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hourStart = selectedHour;
                    minuteStart = selectedMinute;

                    // set current time into textview
                    tvDisplayTime.setText(new StringBuilder().append(pad(hourStart))
                            .append(":").append(pad(minuteStart)));

                    // set current time into timepicker


                }
            };
    private TimePickerDialog.OnTimeSetListener timePickerListenerEnd =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hourEnd = selectedHour;
                    minuteEnd = selectedMinute;

                    // set current time into textview
                    tvDisplayTimeEnd.setText(new StringBuilder().append(pad(hourEnd))
                            .append(":").append(pad(minuteEnd)));

                    // set current time into timepicker


                }
            };
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manual_event, menu);
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
