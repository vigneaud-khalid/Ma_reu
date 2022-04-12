package com.khalid.mareu.model;

import java.util.Date;
import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 *  * Model object representing a Meeting
 */
public class Meeting {
    /** Identifier */
    private long id;

    /** subject */
    private String subject;

    /** place */
    private String place;

    /** time */
    private String time;

    /** attendees */
    private List<String> attendees;

    public Meeting(long id, String subject, String place, String time, List attendees) {
        this.id = id;
        this.subject = subject;
        this.place = place;
        this.time = time;
        this.attendees = attendees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List getAttendees() {
        return attendees;
    }

    public void setAttendees(List attendees) {
        this.attendees = attendees;
    }
}
