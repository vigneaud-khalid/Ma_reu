package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Khalid _  on 29/03/2022.
 */
public class FakeMeetingApiService implements MeetingApiService{

    private List<Meeting> allMeetings = FakeMeetingGenerator.generateMeetings();
    private List<Meeting> filteredMeetings = allMeetings;
    @Override
    public List<Meeting> getMeetings() {
        if(filteredMeetings.isEmpty()){        }
        return filteredMeetings;
    }

    @Override
    public void meetingsNoFilter() {
        filteredMeetings =allMeetings;
    }

    @Override
    public void meetingsDateFilter(String date) {
        List<Meeting> meetingsDateFilter = new ArrayList<>();
        Format f = new SimpleDateFormat("Md");
        String meetingDate;
        for (Meeting meeting: allMeetings) {
            meetingDate = f.format(meeting.getDate());
            if(meetingDate.equals(date)){
            meetingsDateFilter.add(meeting);
            }
        }
        filteredMeetings = meetingsDateFilter;
    }


    @Override
    public void meetingsPlaceFilter(String place) {
        List<Meeting> meetingsPlaceFilter = new ArrayList<>();
        for (Meeting meeting: allMeetings) {
            if(meeting.getPlace().equalsIgnoreCase(place)){
                meetingsPlaceFilter.add(meeting);
            }
        }
        filteredMeetings = meetingsPlaceFilter;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        allMeetings.remove(meeting);
        filteredMeetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(Meeting meeting, boolean isCurrentlyFiltered) {
        if(isCurrentlyFiltered){ filteredMeetings.add(meeting);}
        allMeetings.add(meeting);
        return meeting;
    }
}