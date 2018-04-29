package it472.usna.edu.decker_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewContact extends AppCompatActivity {

    /*
    Class Variables
     */
    EditText fName;
    EditText lName;
    EditText phoneNumber;
    private String contactFileName = "contacts";

    ArrayList<Contact> listContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Get Intent and Extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        listContacts = (ArrayList<Contact>) extras.getSerializable("contacts");

        // Assign EditText fields
        fName = findViewById(R.id.new_1);
        lName = findViewById(R.id.new_2);
        phoneNumber = findViewById(R.id.new_3);

    }


    public void create(View v) {
        Intent intent = new Intent(NewContact.this, ViewContacts1.class);

        // add contact to the list
        Contact newContact = new Contact(fName.getText().toString(), lName.getText().toString(), phoneNumber.getText().toString());
        listContacts.add(newContact);

        //save the list
        saveList();

        // Bundle it and send it
        Bundle extras = new Bundle();
        extras.putSerializable("contacts", (Serializable)listContacts);
        intent.putExtras(extras);
        NewContact.this.startActivity(intent);
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
