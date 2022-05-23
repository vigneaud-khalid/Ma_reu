package com.khalid.mareu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 *  * Model object representing a Meeting
 */
public class Meeting implements Serializable {
    /** Identifier */
    private long id;

    /** avatarColor */
    private int avatarColor;

    /** subject */
    private String subject;

    /** place */
    private String place;

    /** time */
    private Date date;

    /** attendees */
    private List<String> attendees;

    public Meeting(long id, int avatarColor, String subject, String place, Date date, List attendees) {
        this.id = id;
        this.avatarColor = avatarColor;
        this.subject = subject;
        this.place = place;
        this.date = date;
        this.attendees = attendees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvatarColor() { return avatarColor; }

    public void setAvatarColor(int avatarColor) { this.avatarColor = avatarColor;  }

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

    public Date getDate() {  return date; }

    public void setDate(Date date) { this.date = date; }

    public List getAttendees() {
        return attendees;
    }

    public void setAttendees(List attendees) {
        this.attendees = attendees;
    }
}