package com.khalid.mareu.service;

import com.khalid.mareu.model.Meeting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Khalid _  on 29/03/2022.
 */
public abstract class FakeMeetingGenerator {

//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    Date date = formatter.parse("2012-03-04");

    public static List<Meeting> MEETINGS = Arrays.asList(

            new Meeting(1, 1,"Réunion appEDF", "Peach", "15H30", Arrays.asList("herve@lamzone.com", "rachid@uvt.com") ),
            new Meeting(2, 2,"Devel2022", "Room 2", "13H00", Arrays.asList("ed@sst.com", "paul@lamzone.com", "ali@lamzone.com")),
            // new Meeting(3, 3, "Réunion B2", "Pitt", null, null ),
            new Meeting(3,3,"NASA app","Room 3", "08H30", Arrays.asList("ana@lamzone.com", "ed@sst.com","alex@lamzone.com","khalid@gmail.com","ali@lamzone.com")),
            new Meeting(4, 4,"Meeting 33","Kiwi", "11H00", Arrays.asList("igor@lamzone.com", "ali@lamzone.com")),
            new Meeting(5, 5,"Réunion E", "Peach", "17H30", Arrays.asList("paul@lamzone.com", "dav@lamzone.com", "rachid@uvt.com")),
            new Meeting(6, 6,"Project Star", "Room 2", "10H30", Arrays.asList("lyn@lamzone.com", "ed@sst.com","alex@lamzone.com", "dav@lamzone.com", "rachid@uvt.com"))
            // new Meeting(6,"Réunion F","Room 5", date, Arrays.asList("Hello", "aaaa", "fow", "ghi", "lop"))
        );
    // "dd-MM-yyyy"
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }
}