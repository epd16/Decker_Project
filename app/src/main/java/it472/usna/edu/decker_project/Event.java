package it472.usna.edu.decker_project;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by m181446 on 4/28/2018.
 *
 * Is a custom class for an event in the Friend Sked app
 */

public class Event implements Serializable {
    /*
    Class Variables
     */
    private String eventName;
    private java.util.Date eventDate;
    private ArrayList<Contact> eventGuests =new ArrayList<>();

    public Event(String name, java.util.Date date, ArrayList<Contact> guests) {
        eventName = name;
        eventDate = date;
        eventGuests = guests;
    }

    /*
    Public method to return the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /*
    Public method to return the time and date of the event
    */
    public String getEventDateTime() {
        return eventDate.toString();
    }

    /*
    Public method to return the time and date of the event
    */
    public String getEventGuests() {
        return eventGuests.toString();
    }

    /*
    Overrides method toString to return the name of the event and the time
     */
    @Override
    public String toString() {
        return eventName + " at " + eventDate.toString() + " with " + eventGuests.toString();
    }

}
