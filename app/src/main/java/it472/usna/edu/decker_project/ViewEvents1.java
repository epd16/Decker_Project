package it472.usna.edu.decker_project;

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
import java.util.ArrayList;

public class ViewEvents1 extends AppCompatActivity {

    /*
    Class Variables
     */
    private ArrayList<Event> listEvents = new ArrayList<>();
    private ArrayList<String> listNames = new ArrayList<>();
    private ListView eventsListView;
    private ArrayAdapter eventsListAdapter;
    private String eventsFileName = "events";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // set list adapter
        eventsListView = findViewById(R.id.events);

        // Get intent to populate the list of contacts
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listEvents = (ArrayList<Event>) extras.getSerializable("events");

        Log.i("IT472", listEvents.toString());

        // Populate the list of names
        populateNames();

        Log.i("IT472", listNames.toString());

        // Format the list view
        formatListView();

        // Save the List
        saveList();

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Event curItem = (Event) listEvents.get(position);
                Bundle extras = new Bundle();
                extras.putSerializable("event", (Serializable)curItem);
                extras.putSerializable("events", (Serializable)listEvents);

                Intent intent = new Intent(ViewEvents1.this, ViewEvents2.class);
                intent.putExtras(extras);
                ViewEvents1.this.startActivity(intent);
            }
        });
    }

    /*
    Method to navigate to the screen to create an event
     */
    public void home(View v) {
        Intent intent = new Intent(ViewEvents1.this, Home.class);
        ViewEvents1.this.startActivity(intent);
    }

    /*
    Void method to format the list view with list of names

    Source (Custom List/TextView) https://stackoverflow.com/questions/5563698/how-to-change-text-color-of-simple-list-item
    (Also used for list_textview.xml)
    */
    public void formatListView() {
        eventsListAdapter = new ArrayAdapter(this, R.layout.list_textview, listNames);
        eventsListView.setAdapter(eventsListAdapter);
    }

    /*
    Void method to populate the list of names
     */
    public void populateNames() {
        for(int i = 0; i < listEvents.size(); i++) {
            listNames.add(listEvents.get(i).getEventName());
        }
    }

    /*
    Void method to save the list to a file
    */
    public void saveList() {
        try {
            FileOutputStream fos = openFileOutput(eventsFileName, Context.MODE_PRIVATE);
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
