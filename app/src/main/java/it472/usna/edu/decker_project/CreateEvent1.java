package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateEvent1 extends AppCompatActivity {

    /*
    Class Variable
     */
    private EditText nameET;
    private TextView dateTV;
    private TextView timeTV;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private Time time;
    private Date date;
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Set edit and text fields
        nameET = findViewById(R.id.create_e_2);
        dateTV = findViewById(R.id.create_e_4);
        timeTV = findViewById(R.id.create_e_6);

    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void next(View v) {
        Intent intent = new Intent(CreateEvent1.this, CreateEvent2.class);
        CreateEvent1.this.startActivity(intent);
    }

    /*
    Select the Date of the Event
     */
    public void selectDate(View v){
        DatePickerDialogFragment dateDialog = new DatePickerDialogFragment();
        dateDialog.show(getFragmentManager(), "Select Date");

    }

    /*
    Select the Date of the Event
    */
    public void selectTime(View v){
        TimePickerDialogFragment timeDialog = new TimePickerDialogFragment();
        timeDialog.show(getFragmentManager(), "Select Time");
    }

    /*
    Public method to set the date of the event
     */
    public void setDate(String eventDate) {
        dateTV.setText(eventDate);
    }

    /*
    Public method to set the date of the event
    */
    public void setTime(String eventTime) {
        timeTV.setText(eventTime);
    }
}
