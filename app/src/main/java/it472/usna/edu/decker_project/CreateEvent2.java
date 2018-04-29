package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateEvent2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /*
    Method to navigate to the next screen to create an event
    */
    public void next(View v) {
        Intent intent = new Intent(CreateEvent2.this, CreateEvent3.class);
        CreateEvent2.this.startActivity(intent);
    }
}
