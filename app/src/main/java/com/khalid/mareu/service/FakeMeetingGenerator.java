package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Khalid _  on 29/03/2022.
 */
public abstract class FakeMeetingGenerator {

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting(1, 1,"appEDF", "Peach", new Date(2022, 05, 16, 9, 30), Arrays.asList("herve@lamzone.com", "rachid@uvt.com") ),
            new Meeting(2, 2,"Devel2022", "Room2",  new Date(2022, 05, 16, 11, 00), Arrays.asList("ed@sst.com", "paul@lamzone.com", "ali@lamzone.com")),
            new Meeting(3,3,"NASA app","Room3", new Date(), Arrays.asList("ana@lamzone.com", "ed@sst.com","alex@lamzone.com","khalid@gmail.com","ali@lamzone.com")),
            new Meeting(4, 4,"Meeting 33","Kiwi",  new Date(2022, 06, 21, 9, 30), Arrays.asList("igor@lamzone.com", "ali@lamzone.com")),
            new Meeting(5, 5,"Réunion E", "Peach", new Date(2022, 06, 28, 14, 00), Arrays.asList("paul@lamzone.com", "dav@lamzone.com", "rachid@uvt.com")),
            new Meeting(6, 6,"Project Star", "Room2", new Date(2022, 06, 21, 15, 30), Arrays.asList("lyn@lamzone.com", "ed@sst.com","alex@lamzone.com", "dav@lamzone.com", "rachid@uvt.com"))
        );
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }
}