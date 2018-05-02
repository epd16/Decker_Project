package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateEvent2 extends AppCompatActivity {

    /*
    Class Variables
     */

    // Lists of Contacts and Events
    private ArrayList<Contact> listContacts = new ArrayList<>();
    private ArrayList<String> listNames = new ArrayList<>();
    private ArrayList<Contact> listGuests = new ArrayList<>();
    private ArrayList<Event> listEvents = new ArrayList<>();

    // ListView vars
    private ListView contactsListView;
    private ArrayAdapter contactsListAdapter;

    // Temp use variables
    private String name;
    private Date date;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get intent extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");
        listEvents =  (ArrayList<Event>) extras.getSerializable("events");
        name = extras.getString("name");
        date = (Date) extras.getSerializable("date");

        // set list adapter
        contactsListView = findViewById(R.id.guests);

        // Populate the list of names
        populateNames();

        // Format the list view
        formatListView();

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Contact curItem = (Contact) listContacts.get(position);
                curItem.toggleAttendance();
                toast = Toast.makeText(getBaseContext(), curItem.queryAttendance(), Toast.LENGTH_SHORT);
                toast.show();
                Log.i("IT472", curItem.queryAttendance());
            }
        });
    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void next(View v) {
        // generate the list of attendees
        gatherNames();

        // Debugging
        Log.i("IT472", listEvents.toString());

        // declare the intent
        Intent intent = new Intent(CreateEvent2.this, CreateEvent3.class);

        // put in the extras and send
        Bundle extras = new Bundle();
        extras.putSerializable("contacts", (Serializable)listContacts);
        extras.putSerializable("guests", (Serializable)listGuests);
        extras.putSerializable("events", (Serializable)listEvents);
        extras.putSerializable("date", (Serializable)date);
        extras.putString("name", name);
        intent.putExtras(extras);
        CreateEvent2.this.startActivity(intent);
    }

    /*
    Void method to format the list view with list of names

    Source (Custom List/TextView) https://stackoverflow.com/questions/5563698/how-to-change-text-color-of-simple-list-item
    (Also used for list_textview.xml)
    */
    public void formatListView() {
        contactsListAdapter = new ArrayAdapter(this, R.layout.list_textview, listNames);
        contactsListView.setAdapter(contactsListAdapter);
    }

    /*
    Void method to populate the list of names
     */
    public void populateNames() {
        for(int i = 0; i < listContacts.size(); i++) {
            listNames.add(listContacts.get(i).toString());
        }
    }

    public void gatherNames() {
        for(int i = 0; i < listContacts.size(); i++) {
            if(listContacts.get(i).getAttendance()) {
                listGuests.add(listContacts.get(i));
                listContacts.get(i).toggleAttendance();
            }
        }
    }
}
