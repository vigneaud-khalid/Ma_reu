package com.khalid.mareu.events;

import com.khalid.mareu.model.Meeting;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 * Event fired when a user deletes a Meeting
 */
public class DeleteMeetingEvent {
    /**
     * Meeting to delete
     */
    public Meeting meeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}

