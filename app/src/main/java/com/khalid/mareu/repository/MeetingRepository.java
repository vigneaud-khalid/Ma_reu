package com.khalid.mareu.repository;

import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.service.MeetingApiService;

import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class MeetingRepository {

    private final MeetingApiService meetingApiService;

    public MeetingRepository(MeetingApiService meetingApiService) {
        this.meetingApiService = meetingApiService;
        }

        public List <Meeting> getMeetings() {
            return meetingApiService.getMeetings();
        }

        public void deleteMeeting(Meeting meeting) {
            meetingApiService.deleteMeeting(meeting);
        }

        public Meeting createMeeting(Meeting meeting) {
           return meetingApiService.createMeeting(meeting);
        }
}
