package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class CreateEvent3 extends AppCompatActivity {

    /*
    Class Variables
     */

    // Event Details
    private ArrayList<Contact> listContacts = new ArrayList<>();
    private ArrayList<Contact> listGuests = new ArrayList<>();
    private Date date;
    private String name;

    // TextViews
    private TextView nameTV;
    private TextView dateTimeTV;
    private TextView guestsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get extras and assign to variables
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");
        listGuests = (ArrayList<Contact>) extras.getSerializable("guests");
        date = (Date) extras.getSerializable("date");
        name = extras.getString("name");

        // Debugging messages
        Log.i("IT472", listContacts.toString());
        Log.i("IT472", listGuests.toString());
        Log.i("IT472", date.toString());
        Log.i("IT472", name);

        // Assign TVs
        nameTV = findViewById(R.id.create_3_2);
        dateTimeTV = findViewById(R.id.create_3_4);
        guestsTV = findViewById(R.id.create_3_6);

        // Populate TVs
        nameTV.setText(name);
        dateTimeTV.setText(date.toString());
        guestsTV.setText(listGuests.toString());

    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void finish(View v) {
        Intent intent = new Intent(CreateEvent3.this, Home.class);
        CreateEvent3.this.startActivity(intent);
    }
}
