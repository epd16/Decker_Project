package it472.usna.edu.decker_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by m181446 on 4/28/2018.
 */

public class Contact {

    // private class variables
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int lateness;
    private Map <String, String> events = new HashMap<>();

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
}
