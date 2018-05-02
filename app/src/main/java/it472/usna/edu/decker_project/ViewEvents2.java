package it472.usna.edu.decker_project;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ViewEvents2 extends AppCompatActivity {

    /*
    Class Variables
     */
    Event event;
    ArrayList<Event> listEvents = new ArrayList<>();

    // TextViews
    private TextView nameTV;
    private TextView dateTimeTV;
    private TextView guestsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        event = (Event) extras.getSerializable("event");
        listEvents = (ArrayList<Event>) extras.getSerializable("events");

        // Assign TVs
        nameTV = findViewById(R.id.view_2_2);
        dateTimeTV = findViewById(R.id.view_2_4);
        guestsTV = findViewById(R.id.view_2_6);

        // Populate TVs
        nameTV.setText(event.getEventName());
        dateTimeTV.setText(event.getEventDateTime());
        guestsTV.setText(event.getEventGuests());

    }


    /*
    Method to navigate back to main view events screen
    */
    public void back(View v) {
        Intent intent = new Intent(ViewEvents2.this, ViewEvents1.class);
        Bundle extras = new Bundle();
        Log.i("IT472", listEvents.toString());
        extras.putSerializable("events", (Serializable) listEvents);
        intent.putExtras(extras);
        ViewEvents2.this.startActivity(intent);
    }

}
