package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateEvent1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void next(View v) {
        Intent intent = new Intent(CreateEvent1.this, CreateEvent2.class);
        CreateEvent1.this.startActivity(intent);
    }
}
