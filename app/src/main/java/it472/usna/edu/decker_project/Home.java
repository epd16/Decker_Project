package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // set the date
        Calendar c = Calendar.getInstance();
        final SimpleDateFormat curDate = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = curDate.format(c.getTime());
        TextView dateText = (TextView) findViewById(R.id.home_2);
        dateText.setText(dateString);
    }

    /*
    Method to navigate to the screen to create an event
     */
    public void createEvent(View v) {
        Intent intent = new Intent(Home.this, CreateEvent1.class);
        Home.this.startActivity(intent);
    }

    /*
    Method to navigate to the screen to view events
     */
    public void viewEvents(View v) {
        Intent intent = new Intent(Home.this, ViewEvents1.class);
        Home.this.startActivity(intent);
    }

    /*
    Method to view and edit contacts
     */
    public void contacts(View v) {
        Intent intent = new Intent(Home.this, ViewContacts1.class);
        Home.this.startActivity(intent);
    }

}

