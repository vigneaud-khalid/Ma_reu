package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class FakeMeetingApiService implements MeetingApiService{

    //private List<Meeting> meetings = new ArrayList<>();
    private List<Meeting> meetings = FakeMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
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
