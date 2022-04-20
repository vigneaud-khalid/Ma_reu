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
    private List<Meeting> filterdMeetings = allMeetings;
    @Override
    public List<Meeting> getMeetings() {
        return filterdMeetings;
    }

    @Override
    public void meetingsNoFilter() {
        filterdMeetings =allMeetings;
        Log.d("rrrr", " meetingsNoFilter :  " + filterdMeetings.toString());
        Log.d("rrrr", " meetingsNoFilter... :  " + filterdMeetings);
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
        filterdMeetings = meetingsPlaceFilter;
        Log.d("rrrr", " meetingsPlaceFilter");
        Log.d("rrrr", " meetingsPlaceFilter :  " + meetingsPlaceFilter.toString());
        Log.d("rrrr", " meetingsPlaceFilter :  " + filterdMeetings.toString());
        Log.d("rrrr", " meetingsPlaceFilter... :  " + filterdMeetings);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        allMeetings.remove(meeting);
        filterdMeetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(Meeting meeting, String filterOption) {
        Log.d("rrrr", "FakeMeetingApiService _ createMeeting _ filterOption =  "+filterOption);
        Log.d("rrrr", "FakeMeetingApiService _ createMeeting _ meeting.getPlace() =  "+meeting.getPlace());
        if(meeting.getPlace()==filterOption){ filterdMeetings.add(meeting);}
        allMeetings.add(meeting);
        return meeting;
    }
}