package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.service.MeetingApiService;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

    Log.d("rrrr", "AddMeetingActivity ---  onCreate");
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
        Meeting meeting = new Meeting(12,subject, place, "13H30", Arrays.asList("AAAA@lamzone.com", "it@ufo.com"));

        DI.getMeetingRepository().createMeeting(meeting);
        finish();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
