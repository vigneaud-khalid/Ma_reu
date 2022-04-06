package com.khalid.mareu.repository;

import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.service.FakeMeetingApiService;
import com.khalid.mareu.service.MeetingApiService;

import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public class MeetingRepository {

    private static MeetingApiService service = new FakeMeetingApiService();

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
        return service.getMeetings();
    }

    public void deleteMeeting(Meeting meeting) {
        service.deleteMeeting(meeting);
    }

    public Meeting createMeeting(Meeting meeting) {
       return service.createMeeting(meeting);
    }
}

/*public class MeetingRepository {

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
}*/
