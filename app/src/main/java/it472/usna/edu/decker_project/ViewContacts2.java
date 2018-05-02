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
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewContacts2 extends AppCompatActivity {
    /*
    Class variables
    */
    Contact contact;
    ArrayList<Contact> listContacts = new ArrayList<>();
    String contactFileName = "contacts";

    TextView name;
    TextView phoneNumber;
    TextView attended;
    TextView lateness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        contact = (Contact) extras.getSerializable("contact");
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");

        Log.i("IT472", contact.toString());

        populateFields();
    }

    /*
    Method to navigate back to main view events screen
    */
    public void back(View v) {
        Intent intent = new Intent(ViewContacts2.this, ViewContacts1.class);
        Bundle extras = new Bundle();
        Log.i("IT472", listContacts.toString());
        extras.putSerializable("contacts", (Serializable) listContacts);
        intent.putExtras(extras);
        ViewContacts2.this.startActivity(intent);
    }


    /*
    Method to populate fields of the contact
     */
    public void populateFields() {
        name = findViewById(R.id.viewC_2_3);
        phoneNumber = findViewById(R.id.viewC_2_5);
        attended = findViewById(R.id.viewC_2_7);
        lateness = findViewById(R.id.viewC_2_9);

        name.setText(contact.toString());
        phoneNumber.setText(contact.getPhoneNumber());
        attended.setText(Integer.toString(contact.getEvents()));
        lateness.setText(Integer.toString(contact.getLateness()));
    }

    /*
    Public method to launch the recalculation dialog box
     */
    public void recalculate(View v) {
        CustomDialogFragment recalcDialog = new CustomDialogFragment();
        recalcDialog.show(getFragmentManager(), "CustomDialogFragment");
    }

    /*
    Set the lateness to a given value

    @int the value to be used when setting the new lateness
     */
    public void setLateness(int val) {
        contact.setLateness(val);
        lateness.setText(Integer.toString(contact.getLateness()));
        saveList();
    }

    /*
    Average the lateness to a given value

    @int the value to be considered in the average calculation
     */
    public void averageLateness(int val) {
        contact.averageLateness(val);
        lateness.setText(Integer.toString(contact.getLateness()));
        saveList();
    }

    /*
    Void method to save the list to a file
    */
    public void saveList() {
        try {
            // save lateness value in master list
            for(int i = 0; i < listContacts.size(); i++) {
                if(listContacts.get(i).getLastName().equals(contact.getLastName())) {
                    listContacts.get(i).setLateness(contact.getLateness());
                }
            }

            Log.i("IT472", listContacts.toString() + ' ' + listContacts.get(0).getLateness());

            // save to file
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


}
