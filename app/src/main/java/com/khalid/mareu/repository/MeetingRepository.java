package com.khalid.mareu.repository;

import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.service.FakeMeetingApiService;
import com.khalid.mareu.service.MeetingApiService;

import java.util.List;

/**
 * Created by Khalid _  on 29/03/2022.
 */
public class MeetingRepository {

    private static MeetingApiService service = new FakeMeetingApiService();
    private String filterOption;
     /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new FakeMeetingApiService();
    }

    public List <Meeting> getMeetings() {
        List<Meeting> meetingList = service.getMeetings();
//        if (meetingList.isEmpty()){ onCreateDialog();}
        return meetingList;
    }

    public void meetingsNoFilter(){
        filterOption = "noFilter";
        service.meetingsNoFilter();
    }

    public void meetingsPlaceFilter(String place) {
        filterOption = place;
        service.meetingsPlaceFilter(place);
    }

    public void meetingsDateFilter(String date) {
        filterOption = date;
        service.meetingsDateFilter(date);
    }

    public void deleteMeeting(Meeting meeting) {
        service.deleteMeeting(meeting);
    }

    public Meeting createMeeting(Meeting meeting) {
        boolean isCurrentlyFiltered = meeting.getPlace().equalsIgnoreCase(filterOption);
       return service.createMeeting(meeting, isCurrentlyFiltered);
    }
}
