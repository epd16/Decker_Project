package it472.usna.edu.decker_project;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewContacts1 extends AppCompatActivity {

    /*
    Class variables
     */
    private ArrayList<Contact> listContacts = new ArrayList<>();
    private ArrayList<String> listNames = new ArrayList<>();
    private ListView contactsListView;
    private ArrayAdapter contactsListAdapter;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get intent to populate the list of contacts
        Intent intent = new Intent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) intent.getExtras().getSerializable("contacts");

        // Populate the list of names
        populateNames();

        // Format the list view


    }

    /*
    Method to navigate to the screen to create an event
     */
    public void home(View v) {
        Intent intent = new Intent(ViewContacts1.this, Home.class);
        ViewContacts1.this.startActivity(intent);
    }

    /*
    Void method to format the list view with list of names
    */
    public void formatListView() {
        contactsListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listNames);
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

}
