package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;

import java.util.Arrays;
import java.util.Date;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
     }

    public void CheckBoxButtonHandler(View view) {

        // add the attendee checked
        // attendees.add( COMMENT LE PASSER EN PARAM ????  )
    }

    public void submitButtonHandler(View view) {
        // récupérer l'id du  dernier meeting et l'incrémenter

        EditText subjectEditText = (EditText) findViewById(R.id.subject);
        String subject = subjectEditText.getText().toString();
        EditText placeEditText = (EditText) findViewById(R.id.place);
        String place = placeEditText.getText().toString();
        // create a new meeting and add it to the list
        Meeting meeting = new Meeting(12,subject, place, new Date(), Arrays.asList("maxime@lamzone.com", "alex@lamzone.com"));

        DI.getMeetingRepository().createMeeting(meeting);
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, FormActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}