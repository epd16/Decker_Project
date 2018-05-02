package it472.usna.edu.decker_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateEvent3 extends AppCompatActivity {

    /*
    Class Variables
     */

    // Lists of Contacts and Events
    private ArrayList<Contact> listContacts = new ArrayList<>();
    private ArrayList<Contact> listGuests = new ArrayList<>();
    private ArrayList<Event> listEvents = new ArrayList<>();

    // Event Details
    private Date dateTime;
    private String name;

    // TextViews
    private TextView nameTV;
    private TextView dateTimeTV;
    private TextView guestsTV;

    // Misc
    String eventFileName = "events";

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
        listEvents =  (ArrayList<Event>) extras.getSerializable("events");
        dateTime = (Date) extras.getSerializable("date");
        name = extras.getString("name");

        // Assign TVs
        nameTV = findViewById(R.id.create_3_2);
        dateTimeTV = findViewById(R.id.create_3_4);
        guestsTV = findViewById(R.id.create_3_6);

        // Populate TVs
        nameTV.setText(name);
        dateTimeTV.setText(dateTime.toString());
        guestsTV.setText(listGuests.toString());

    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void finish(View v) {

        // Create the new event
        Event newEvent = new Event(name, dateTime, listGuests);

        // Add it to the list
        listEvents.add(newEvent);

        // Save the list
        saveList();

        // Send the text messages
        sendInvites();

        Intent intent = new Intent(CreateEvent3.this, Home.class);
        Bundle extras = new Bundle();
        extras.putSerializable("contacts", (Serializable)listContacts);
        extras.putSerializable("events", (Serializable)listEvents);
        intent.putExtras(extras);
        CreateEvent3.this.startActivity(intent);
    }

    /*
    Void method to save the list to a file
    */
    public void saveList() {
        try {
            FileOutputStream fos = openFileOutput(eventFileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream((fos));
            oos.writeObject(listEvents);
            oos.close();

        } catch (FileNotFoundException e) {
            Log.e("IT472", "saveObjectToFile FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("IT472", "saveObjectToFile IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("IT472", "saveObjectToFile Exception:  " + e.getMessage());
        }
    }

    /*
    Controller method to send the invites out to the contacts with adjusted times
     */
    public void sendInvites() {
        // Temp variables for date and time and current contact
        Contact currentContact;
        Date inviteDateTime;

        // String used for the text message
        String message;

        // Iterate through the list
        for(int i = 0; i < listGuests.size(); i++) {
            // Current Contact
            currentContact = listGuests.get(i);
            // Fetch the adjusted time
            inviteDateTime = adjustedTime(currentContact);
            Log.i("IT472", "Sending to " + currentContact.getFirstName() + " " + inviteDateTime.toString());

            // Generate the text messages
            message = "You're invited! \nEvent: " + name + '\n' + "Time & Date: " + inviteDateTime;
            Log.i("IT472", "To " + currentContact.getPhoneNumber() + " " + message);
            android.telephony.SmsManager sms = android.telephony.SmsManager.getDefault();
            sms.sendTextMessage(currentContact.getPhoneNumber(), null, message, null, null);

        }

        // Send each text based on the avg lateness


    }

    /*
    Public method to adjust the time of an event for an invite for a given contact.

    @Contact is the given contact with a lateness value to be used for the adjustment
     */
    public Date adjustedTime(Contact contact) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTime);
        c.add(Calendar.MINUTE, -contact.getLateness());
        return c.getTime();
    }
}
