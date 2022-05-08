package com.khalid.mareu.service;

import android.util.Log;

import com.khalid.mareu.model.Meeting;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Khalid _  on 29/03/2022.
 */
public class FakeMeetingApiService implements MeetingApiService{

    // private List<Meeting> meetings = new ArrayList<>();
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
        Log.d("rrrr", " meetingsNoFilter :  " + filteredMeetings.toString());
        Log.d("rrrr", " meetingsNoFilter... :  " + filteredMeetings);
    }

    @Override
    public void meetingsDateFilter(String date) {
        List<Meeting> meetingsDateFilter = new ArrayList<>();
        Format f = new SimpleDateFormat("Md");
        String meetingDate;
        for (Meeting meeting: allMeetings) {
            meetingDate = f.format(meeting.getDate());
            Log.d("rrrr", " meetingsDateFilter... : meetingDate = " + meetingDate);
            Log.d("rrrr", " meetingsDateFilter... :  date = " + date);
            if(meetingDate.equals(date)){
            meetingsDateFilter.add(meeting);
            }
        }
        filteredMeetings = meetingsDateFilter;
        Log.d("rrrr", " meetingsDateFilter");
        Log.d("rrrr", " meetingsDateFilter :  " + meetingsDateFilter.toString());
        Log.d("rrrr", " meetingsDateFilter :  " + filteredMeetings.toString());
        Log.d("rrrr", " meetingsDateFilter... :  " + filteredMeetings);
    }


    @Override
    public void meetingsPlaceFilter(String place) {
        List<Meeting> meetingsPlaceFilter = new ArrayList<>();
        for (Meeting meeting: allMeetings) {
            if(meeting.getPlace()==place){meetingsPlaceFilter.add(meeting);}
        }
        filteredMeetings = meetingsPlaceFilter;
        Log.d("rrrr", " meetingsPlaceFilter");
        Log.d("rrrr", " meetingsPlaceFilter :  " + meetingsPlaceFilter.toString());
        Log.d("rrrr", " meetingsPlaceFilter :  " + filteredMeetings.toString());
        Log.d("rrrr", " meetingsPlaceFilter... :  " + filteredMeetings);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        Log.d("rrrrr", "FakeMeetingApiService _ deleteMeeting() _ meeting.getDate().toString()  "+ meeting.getDate().toString());
        allMeetings.remove(meeting);
        filteredMeetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(Meeting meeting, String filterOption) {
        Log.d("rrrr", "FakeMeetingApiService _ createMeeting _ filterOption =  "+filterOption);
        Log.d("rrrr", "FakeMeetingApiService _ createMeeting _ meeting.getPlace() =  "+meeting.getPlace());
        if(meeting.getPlace()==filterOption){ filteredMeetings.add(meeting);}
        allMeetings.add(meeting);
        return meeting;
    }
}