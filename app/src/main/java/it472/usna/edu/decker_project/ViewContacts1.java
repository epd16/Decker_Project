package it472.usna.edu.decker_project;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
    private String contactFileName = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // set list adapter
        contactsListView = findViewById(R.id.contacts);

        // Get intent to populate the list of contacts
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");

        Log.i("IT472", listContacts.toString());

        // Populate the list of names
        populateNames();

        Log.i("IT472", listNames.toString());

        // Format the list view
        formatListView();

        // Save the List
        saveList();

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Contact curItem = (Contact) listContacts.get(position);
                Bundle extras = new Bundle();
                extras.putSerializable("contact", (Serializable)curItem);
                extras.putSerializable("contacts", (Serializable)listContacts);

                Intent intent = new Intent(ViewContacts1.this, ViewContacts2.class);
                intent.putExtras(extras);
                ViewContacts1.this.startActivity(intent);
            }
        });


    }

    /*
    Method to navigate to the screen to go home
     */
    public void home(View v) {
        Intent intent = new Intent(ViewContacts1.this, Home.class);
        ViewContacts1.this.startActivity(intent);
        saveList();
    }

    /*
    Method to navigate to the screen to create an contact
    */
    public void newContact(View v) {
        Intent intent = new Intent(ViewContacts1.this, NewContact.class);
        Bundle extras = new Bundle();
        extras.putSerializable("contacts", (Serializable)listContacts);
        intent.putExtras(extras);
        ViewContacts1.this.startActivity(intent);
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

    /*
Void method to save the list to a file
*/
    public void saveList() {
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


}
