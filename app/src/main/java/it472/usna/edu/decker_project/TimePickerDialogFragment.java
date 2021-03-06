package it472.usna.edu.decker_project;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/*
Time Picker dialog fragment used from the IT472 Course Notes
Used to select a time for a given event.
 */
public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance(); //current date and time

        TimePickerDialog timeDlg = new TimePickerDialog(getActivity(), this,
                c.get(Calendar.HOUR_OF_DAY), //default hour
                c.get(Calendar.MINUTE),  //default minute
                true);  //is24Hour

        return timeDlg;
    }

    //hour is a 24 hour format
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        //The method onTimeSet is called once even if the back key is pressed,
        // and is called twice if ‘Done’ is selected.  This is a known issue!
        Log.d("IT472", "Time Selected: " + hourOfDay + ":" + minute);
        String time = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        //String time = hourOfDay + ":" + minute;
        ((CreateEvent1) getActivity()).setTime(time);
    }
}
