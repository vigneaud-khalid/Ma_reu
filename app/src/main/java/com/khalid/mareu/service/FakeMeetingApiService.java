package com.khalid.mareu.service;

import android.util.Log;

import com.khalid.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class FakeMeetingApiService implements MeetingApiService{

    private List<Meeting> meetings = new ArrayList<>();
    private List<Meeting> allMeetings = FakeMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void meetingsNoFilter() {
        meetings=allMeetings;
    }

    @Override
    public List<Meeting> getMeetingsWithFilter(String filter) {
        return null;
    }

    @Override
    public void meetingsPlaceFilter(String place) {
        List<Meeting> meetingsPlaceFilter = new ArrayList<>();
        for (Meeting meeting: meetings) {
            if(meeting.getPlace()==place){meetingsPlaceFilter.add(meeting);}
        }
        meetings = meetingsPlaceFilter;
        Log.d("rrrr", " meetingsPlaceFilter");
        Log.d("rrrr", " meetingsPlaceFilter :  " + meetingsPlaceFilter.toString());
        Log.d("rrrr", " meetingsPlaceFilter :  " + meetings.toString());
        Log.d("rrrr", " meetingsPlaceFilter... :  " + meetings);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        meetings.add(meeting);
        return meeting;
    }
}