package it472.usna.edu.decker_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by m181446 on 5/2/2018.
 */


public class CustomDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    EditText inputET;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View layout = inflater.inflate(R.layout.custom_dialog, null);
        inputET = (EditText) layout.findViewById(R.id.inputET);

        builder.setView(layout); //set the view in dialog to custom layout
        builder.setTitle("Recalculate Lateness:\nEnter a Value in Minutes")
                .setPositiveButton("Set", this)
                .setNeutralButton("Average", this)
                .setNegativeButton("Cancel", this);
        return builder.create();
    }

    public void onClick(DialogInterface dialog, int itemId) {

        if (itemId == Dialog.BUTTON_POSITIVE) {
            Log.d("IT472", "Setting lateness to " + inputET.getText());
            ((ViewContacts2) getActivity()).setLateness(Integer.parseInt(inputET.getText().toString()));

        } else if(itemId == Dialog.BUTTON_NEUTRAL){
            Log.d("IT472", "Averaging lateness with " + inputET.getText());
            ((ViewContacts2) getActivity()).averageLateness(Integer.parseInt(inputET.getText().toString()));
        } else {
            Log.d("IT472", "Cancelled");
        }

        inputET.setText("");
    }

}
