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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        // filterOption = getIntent().getExtras().getString("filterOption","noFilter");
        // Log.d("rrrr", "AddMeetingActivity _ onCreate _ filterOption =  "+filterOption);

        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) findViewById(R.id.autocomplete_attendees);
        String[] attendees = getResources().getStringArray(R.array.attendees_array);
        Log.d("rrrr", "AddMeetingActivity _ sMultiAutoCompleteTextView _ attendees =  "+attendees);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendees);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }

    public void CheckBoxButtonHandler(View view) {

        // add the attendee checked
        // attendees.add( COMMENT LE PASSER EN PARAM ????  )
    }

    public void submitButtonHandler(View view) {
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ before interfering with the id");
        // récupérer l'id du  dernier meeting et l'incrémenter
//        int numberMeetings = DI.getMeetingRepository().getMeetings().size();
//        long lastId = DI.getMeetingRepository().getMeetings().get(numberMeetings).getId();
//        long id = lastId++;
//        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ lastId =  "+lastId);
//        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ id =  "+id);

        EditText subjectEditText = (EditText) findViewById(R.id.subject);
        String subject = subjectEditText.getText().toString();
        // control of the field
        if (subjectEditText.getText().toString().isEmpty()) {
            subjectEditText.setError("YOU HAVE TO NAME THE SUBJECT  !!!");
            return;
        }
        EditText placeEditText = (EditText) findViewById(R.id.place);
        String place;
        // control of the field
        if (placeEditText.getText().toString() != null) {
            place = placeEditText.getText().toString();
        } else {
            // désactiver le submitButton???
            subjectEditText.setText("YOU HAVE TO NAME THE PLACE  !!!");
            place = "";
        }



        // create a new meeting and add it to the list
        if (subject != "" && place != "") {
            Meeting meeting = new Meeting(12, randomNumber(), subject, place, "13H30", Arrays.asList("AAAA@lamzone.com", "it@ufo.com"));
            Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ place =  " + place);
            DI.getMeetingRepository().createMeeting(meeting);
            ListMeetingActivity.navigate(this);
        }

    }
    public int randomNumber(){
        return ThreadLocalRandom.current().nextInt(1, 13);
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
