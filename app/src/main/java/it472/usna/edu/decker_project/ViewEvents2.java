package it472.usna.edu.decker_project;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewEvents2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }


    /*
    Method to navigate back to main view events screen
    */
    public void back(View v) {
        Intent intent = new Intent(ViewEvents2.this, ViewEvents1.class);
        ViewEvents2.this.startActivity(intent);
    }

}
