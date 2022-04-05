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
 * Created by ordinateur _ Khalid _  on 29/03/2022.
 */
public abstract class FakeMeetingGenerator {

//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    Date date = formatter.parse("2012-03-04");


    public static List<Meeting> MEETINGS = Arrays.asList(


            new Meeting(1, "Réunion A", "Peach", null, null ),
            //new Meeting(2, "Réunion B", "Room 2", new Date(), Collections.singletonList("Delhi"))
            //
            // new Meeting(4,"Réunion C","Room 3", 2012-03-04, null),
            new Meeting(4,"Réunion D","Room 4", new Date(), Arrays.asList("maxime@lamzone.com", "alex@lamzone.com"))
            // new Meeting(5,"Réunion E","Room 5", date, Arrays.asList("Hello", "World!", "How", "Are", "You"))
        );

    // "dd-MM-yyyy"
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }
}
