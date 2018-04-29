package it472.usna.edu.decker_project;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by m181446 on 4/28/2018.
 */

public class Event implements Serializable {
    /*
    Class Variables
     */
    private String eventName;
    private Time eventTime;
    private Date eventDate;
    private Calendar cal = Calendar.getInstance();

    public Event(String name, Time time, Date date) {
        eventName = name;
        eventTime = time;
        eventDate = date;

    }


}
