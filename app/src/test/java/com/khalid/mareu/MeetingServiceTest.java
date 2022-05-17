package com.khalid.mareu;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.util.Log;

import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.repository.MeetingRepository;
import com.khalid.mareu.service.FakeMeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
    public void setup() {
        repo = DI.getNewInstanceRepository();
    }

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
}
