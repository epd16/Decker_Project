package it472.usna.edu.decker_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Home extends AppCompatActivity {

    /*
    Class Variables
     */

    // Pertaining to the contact list
    private String contactFileName = "contacts";
    private ArrayList<Contact> listContacts = new ArrayList<>();

    // Pertaining to the event list
    private String eventFileName = "events";
    private ArrayList<Event> listEvents = new ArrayList<>();

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

        //Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
       // Event temp = new Event("Temp-1", date, listContacts);
        //listEvents.add(temp);
        //saveEventList();



        // load the contacts
        loadContactList();
        Log.i("IT472", "HOME: CONTACTS ARE: " + listContacts.toString());

        // load the events
        loadEventList();
        Log.i("IT472", "HOME: EVENTS ARE: " + listEvents.toString());

        //listContacts.get(0).setLateness(5);
        //listContacts.get(0).toggleAttendance();

        // UNCOMMENT TO WIPE THE CONTACT LIST
        //listContacts.clear();
        //saveContactList();

        // UNCOMMENT TO WIPE THE CONTACT LIST
        //listEvents.clear();
        //saveEventList();

        // Debug log message
        //Log.i("IT472", listContacts.toString());
    }


    // ************************
    // BEGIN NAVIGATION METHODS
    // ************************

    /*
    Method to navigate to the screen to create an event
     */
    public void createEvent(View v) {
        Intent intent = new Intent(Home.this, CreateEvent1.class);
        Bundle homeBundle = new Bundle();
        homeBundle.putSerializable("contacts", (Serializable)listContacts);
        homeBundle.putSerializable("events", (Serializable)listEvents);
        intent.putExtras(homeBundle);
        Home.this.startActivity(intent);
    }

    /*
    Method to navigate to the screen to view events
     */
    public void viewEvents(View v) {
        Intent intent = new Intent(Home.this, ViewEvents1.class);
        Bundle homeBundle = new Bundle();
        homeBundle.putSerializable("events", (Serializable)listEvents);
        intent.putExtras(homeBundle);
        Home.this.startActivity(intent);
    }

    /*
    Method to view and edit contacts
     */
    public void contacts(View v) {
        Intent intent = new Intent(Home.this, ViewContacts1.class);
        Bundle homeBundle = new Bundle();
        homeBundle.putSerializable("contacts", (Serializable)listContacts);
        intent.putExtras(homeBundle);
        Home.this.startActivity(intent);
    }

    // **********************
    // END NAVIGATION METHODS
    // **********************

    /*
    Void method to load the contact list
    */
    public void loadContactList() {
        try {
            FileInputStream fis = openFileInput(contactFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object object = ois.readObject();
            listContacts = (ArrayList) object;
            ois.close();

        } catch (FileNotFoundException e) {
            Log.e("IT472", "getObjectFromFile FileNotFoundException: " + e.getMessage());
            saveContactList();
        } catch (IOException e) {
            Log.e("IT472", "getObjectFromFile IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("IT472", "getObjectFromFile ClassNotFoundException: " + e.getMessage());
        } catch (Exception e) {// Catch exception if any
            Log.e("IT472", "getObjectFromFile Exception: " + e.getMessage());
        }
    }

    /*
    Void method to save the contact list to a file
    */
    public void saveContactList() {
        try {
            FileOutputStream fos = openFileOutput(contactFileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream((fos));
            oos.writeObject(listContacts);
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
    Void method to load the event list
    */
    public void loadEventList() {
        try {
            FileInputStream fis = openFileInput(eventFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object object = ois.readObject();
            listEvents = (ArrayList) object;
            ois.close();

        } catch (FileNotFoundException e) {
            Log.e("IT472", "getObjectFromFile FileNotFoundException: " + e.getMessage());
            saveEventList();
        } catch (IOException e) {
            Log.e("IT472", "getObjectFromFile IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("IT472", "getObjectFromFile ClassNotFoundException: " + e.getMessage());
        } catch (Exception e) {// Catch exception if any
            Log.e("IT472", "getObjectFromFile Exception: " + e.getMessage());
        }
    }

    /*
    Void method to save the event list to a file
    */
    public void saveEventList() {
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

}

