package com.khalid.mareu.service;

import android.util.Log;

import com.khalid.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Khalid _  on 29/03/2022.
 */
public class FakeMeetingApiService implements MeetingApiService{

    // private List<Meeting> meetings = new ArrayList<>();
    private List<Meeting> allMeetings = FakeMeetingGenerator.generateMeetings();
    private List<Meeting> meetings = allMeetings;
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void meetingsNoFilter() {
        meetings=allMeetings;
        Log.d("rrrr", " meetingsNoFilter :  " + meetings.toString());
        Log.d("rrrr", " meetingsNoFilter... :  " + meetings);
    }

    @Override
    public void meetingsWithDateFilter(String filter) {
        // todo
    }

    @Override
    public void meetingsPlaceFilter(String place) {
        List<Meeting> meetingsPlaceFilter = new ArrayList<>();
        for (Meeting meeting: allMeetings) {
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
        allMeetings.remove(meeting);
        meetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        meetings.add(meeting);
        allMeetings.add(meeting);
        return meeting;
    }
}