package it472.usna.edu.decker_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/*
A splash screen that could act as a login screen. Initiates when app is first opened
 */

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /*
    Method to navigate to the home screen
    */
    public void enter(View v) {
        Intent intent = new Intent(Splash.this, Home.class);
        Splash.this.startActivity(intent);
    }

}
