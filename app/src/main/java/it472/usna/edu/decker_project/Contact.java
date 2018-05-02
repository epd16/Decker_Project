package it472.usna.edu.decker_project;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Contact class used for the contact list
 */

public class Contact implements Serializable {

    // private class variables
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int lateness;
    private boolean attending;

    public Contact(String fName, String lName, String pNumber) {
        firstName = fName;
        lastName = lName;
        phoneNumber = pNumber;
        lateness = 0;
        attending = false;
    }

    /*
    Method to set firstName to a given name
    @String is the name to be set
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /*
    Method to get firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /*
    Method to set lastName to a given name
    @String is the name to be set
 */
    public void setLastName(String name) {
        lastName = name;
    }

    /*
    Method to get lastName
     */
    public String getLastName() {
        return lastName;
    }

    /*
    Method to set phoneNumber to a given name
    @String is the number to be set
 */
    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }

    /*
    Method to get firstName
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*
    Method to get lateness value
     */
    public int getLateness() {
        return lateness;
    }

    /*
    Method to get lateness value
    */
    public void setLateness(int newLateness) {
        lateness = newLateness;
    }

    /*
    Method overriding toString()
    Returns the first and last name of the contact
     */
    @Override
    public String toString() {
       return firstName + " " + lastName;
    }

    /*
    Method to toggle attendance for a specific event
     */
    public void toggleAttendance() {
        if(attending) { attending = false; }
        else { attending = true; }
    }

    /*
    Method to query attendance, returns a string
     */
    public String queryAttendance() {
        if(attending) { return firstName + " is attending"; }
        else { return firstName + " is not attending"; }
    }

    /*
    Method to check attendance, returns a boolean
    */
    public boolean getAttendance() {
        if(attending) { return true; }
        else { return false; }
    }
}
