package it472.usna.edu.decker_project;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance(); //current date and time

        DatePickerDialog dateDlg = new DatePickerDialog(getActivity(), this,
                c.get(Calendar.YEAR),  //set default year
                c.get(Calendar.MONTH), //set default month
                c.get(Calendar.DAY_OF_MONTH)); //set default day

        return dateDlg;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        //The method onDateSet is called once even if the back key is pressed,
        // and is called twice if ‘Done’ is selected.  This is a known issue!

        Log.d("IT472", "Date Selected: " + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
        String date = Integer.toString(monthOfYear+1) + "-" + Integer.toString(dayOfMonth) + "-" + Integer.toString(year);
        ((CreateEvent1) getActivity()).setDate(date);
    }
}
