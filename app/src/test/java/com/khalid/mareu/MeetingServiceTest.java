package com.khalid.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.repository.MeetingRepository;
import com.khalid.mareu.service.FakeMeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ordinateur _ Khalid _  on 16/05/2022.
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {
    private MeetingRepository repo;

    @Before
    public void setup() { repo = DI.getNewInstanceRepository();   }

    /**
     *  we ensure that all meetings are listed
     */
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = repo.getMeetings();
        List<Meeting> expectedMeetings = FakeMeetingGenerator.MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     *  we ensure that a meeting is deleted
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = repo.getMeetings().get(0);
        repo.deleteMeeting(meetingToDelete);
        assertFalse(repo.getMeetings().contains(meetingToDelete));
    }

    /**
     *  we ensure that a meeting is created
     */
    @Test
    public void createMeetingsWithSuccess() {
        Meeting newMeeting = new Meeting(25, 1,"Test", "Kiwi", new Date(2022, 06, 21, 15, 30), Arrays.asList("ed@sst.com","alex@lamzone.com", "dav@lamzone.com") );
        Meeting createdMeeting = repo.createMeeting(newMeeting);
        List<Meeting> meetings = repo.getMeetings();
        assertTrue(meetings.contains(newMeeting));
    }

     /**
     *  we ensure that meetings with PlaceFilter are listed
     */
    @Test
    public void getPlaceFilteredMeetingsWithSuccess() {
        List<Meeting> placeRoom2Meetings = new ArrayList<>();
        List<Meeting> meetings = repo.getMeetings();
        Meeting meeting1 = meetings.get(1);
        Meeting meeting5 = meetings.get(5);
        placeRoom2Meetings.add(meeting1);
        placeRoom2Meetings.add(meeting5);
        repo.meetingsPlaceFilter("Room2");
        List<Meeting> expectedPlaceRoom2Meetings = repo.getMeetings();
        assertThat(placeRoom2Meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedPlaceRoom2Meetings.toArray()));
    }

    /**
     *  we ensure that meetings with DateFilter are listed
     */
    @Test
    public void getDateFilteredMeetingsWithSuccess() {
        List<Meeting> DateJul21Meetings = new ArrayList<>();
        List<Meeting> meetings = repo.getMeetings();
        Meeting meeting3 = meetings.get(3);
        Meeting meeting5 = meetings.get(5);
        DateJul21Meetings.add(meeting3);
        DateJul21Meetings.add(meeting5);
        repo.meetingsDateFilter("721");
        List<Meeting> expectedDateFilteredMeetings = repo.getMeetings();
        assertThat(DateJul21Meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedDateFilteredMeetings.toArray()));
    }

    /**
     *  we ensure that meetings with NoFilter are listed
     */
    @Test
    public void getNoFilteredMeetingsWithSuccess() {
        List<Meeting> noFilteredMeetings = new ArrayList<>();
        noFilteredMeetings.addAll(repo.getAllMeetings());
        //Meeting meetingToDelete = repo.getMeetings().get(0);
        //Meeting meetingToDelete2 = repo.getMeetings().get(2);
        //noFilteredMeetings.remove(meetingToDelete);
        repo.meetingsNoFilter();
        //repo.deleteMeeting(meetingToDelete2);
        List<Meeting> expectedNoFilteredMeetings = repo.getMeetings();
        assertThat(noFilteredMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNoFilteredMeetings.toArray()));
    }




}

