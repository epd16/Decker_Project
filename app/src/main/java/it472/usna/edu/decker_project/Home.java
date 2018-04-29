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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Home extends AppCompatActivity {

    /*
    Class Variables
     */
    private String fileName = "contacts";
    private ArrayList<Contact> listContacts = new ArrayList<>();

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

        // load the contacts
        loadList();

        Contact ted = new Contact("ted", "decker", "3123404300");
        listContacts.add(ted);
    }


    // ************************
    // BEGIN NAVIGATION METHODS
    // ************************

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
        Bundle homeBundle = new Bundle();
        homeBundle.putSerializable("contacts", (Serializable)listContacts);
        intent.putExtras(homeBundle);
        Home.this.startActivity(intent);
    }

    // **********************
    // END NAVIGATION METHODS
    // **********************

    /*
    Void method to load the to-do list
    */
    public void loadList() {
        try {
            FileInputStream fis = openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object object = ois.readObject();
            listContacts = (ArrayList) object;
            ois.close();

        } catch (FileNotFoundException e) {
            Log.e("IT472", "getObjectFromFile FileNotFoundException: " + e.getMessage());
            saveList();
        } catch (IOException e) {
            Log.e("IT472", "getObjectFromFile IOException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("IT472", "getObjectFromFile ClassNotFoundException: " + e.getMessage());
        } catch (Exception e) {// Catch exception if any
            Log.e("IT472", "getObjectFromFile Exception: " + e.getMessage());
        }
    }

    /*
    Void method to save the list to a file
    */
    public void saveList() {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
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

}

