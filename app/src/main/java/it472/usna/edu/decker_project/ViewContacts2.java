package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewContacts2 extends AppCompatActivity {
    /*
    Class variables
    */
    Contact contact;
    ArrayList<Contact> listContacts = new ArrayList<>();

    TextView name;
    TextView phoneNumber;
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
        lateness = findViewById(R.id.viewC_2_7);

        name.setText(contact.toString());
        phoneNumber.setText(contact.getPhoneNumber());
        lateness.setText(Integer.toString(contact.getLateness()));
    }
}
