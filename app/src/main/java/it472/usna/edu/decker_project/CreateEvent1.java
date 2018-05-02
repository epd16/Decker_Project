package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateEvent1 extends AppCompatActivity {

    /*
    Class Variables
     */

    // TextViews
    private EditText nameET;
    private TextView dateTV;
    private TextView timeTV;

    // Date and time vars
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    private Date date;
    private String tempDate;
    private String tempTime;

    // Contact and Event Lists
    private ArrayList<Contact> listContacts = new ArrayList<>();
    private ArrayList<Event> listEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get intent extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");
        listEvents =  (ArrayList<Event>) extras.getSerializable("events");

        // Set edit and text fields
        nameET = findViewById(R.id.create_e_2);
        dateTV = findViewById(R.id.create_e_4);
        timeTV = findViewById(R.id.create_e_6);

    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void next(View v) {
        // condense the date
        condenseDT(tempDate, tempTime);

        Intent intent = new Intent(CreateEvent1.this, CreateEvent2.class);
        Bundle extras = new Bundle();

        // add in the extras
        extras.putSerializable("contacts", (Serializable)listContacts);
        extras.putString("name", nameET.getText().toString());
        extras.putSerializable("date", (Serializable)date);
        extras.putSerializable("events", (Serializable)listEvents);

        intent.putExtras(extras);
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
        tempDate = eventDate;
        dateTV.setText(eventDate);
    }

    /*
    Public method to set the date of the event
    */
    public void setTime(String eventTime) {
        tempTime = eventTime;
        timeTV.setText(eventTime);
    }

    /*
    Public method to condense date and time into one formatted item
     */
    public void condenseDT(String tDate, String tTime) {
        String tempDT = tDate + " " + tTime;
        try {
            date = dateFormat.parse(tempDT);
            Log.i("IT472", date.toString());
        } catch (java.text.ParseException e) {
            Log.i("IT472", "Parse Error");
        }
    }
}
