package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewEvents1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /*
    Method to navigate to the screen to create an event
     */
    public void home(View v) {
        Intent intent = new Intent(ViewEvents1.this, Home.class);
        ViewEvents1.this.startActivity(intent);
    }
}
