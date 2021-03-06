package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;
import java.util.List;

/**
 * Created by Khalid _  on 29/03/2022.
 */
public interface MeetingApiService {

    /**
     * Get Meetings
     * @return {@link List}
     */
    public List<Meeting> getMeetings();

    /**
     * Get all Meetings (noFilter)
     * @return {@link List}
     */
    public List<Meeting> getAllMeetings();

    /**
     * Modify meeting list with no filter
     */
    public void meetingsNoFilter();

    /**
     * Modify meeting list with date filter
     */
    public void meetingsDateFilter(String date);

    /**
     * Modify meeting list with place filter
     */
    public void meetingsPlaceFilter(String place);

    /**
     * Deletes a meeting
     * @param meeting
     */
    public void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     * @return Meeting
     */
    public Meeting createMeeting(Meeting meeting, boolean isCurrentlyFiltered);
}
