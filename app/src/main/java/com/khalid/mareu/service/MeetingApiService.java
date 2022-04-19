package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;

import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public interface MeetingApiService {

    /**
     * Get all Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Get Meetings with no filter
     * @return {@link List}
     */
    public void meetingsNoFilter();

    /**
     * Get Meetings with filter
     * @return {@link List}
     */

    List<Meeting> getMeetingsWithFilter(String filter);

    /**
     * Modify Meetings with Place filter
     * @return {@link List}
     */
    public void meetingsPlaceFilter(String place);

    /**
     * Deletes a Meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     * @return
     */
    Meeting createMeeting(Meeting meeting);
}
