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

    String filterOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        filterOption = getIntent().getExtras().getString("filterOption","noFilter");
        Log.d("rrrr", "AddMeetingActivity _ onCreate _ filterOption =  "+filterOption);

    }

    public void CheckBoxButtonHandler(View view) {

        // add the attendee checked
        // attendees.add( COMMENT LE PASSER EN PARAM ????  )
    }

    public void submitButtonHandler(View view) {
        // récupérer l'id du  dernier meeting et l'incrémenter

        EditText subjectEditText = (EditText) findViewById(R.id.subject);
        String subject = "Today Meeting";
        subjectEditText.getText().toString();
        EditText placeEditText = (EditText) findViewById(R.id.place);
        String place = placeEditText.getText().toString();

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_attendees);
        String[] attendees = getResources().getStringArray(R.array.attendees_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendees);
        textView.setAdapter(adapter);

        // create a new meeting and add it to the list
        place = "Peach";
        Meeting meeting = new Meeting(12, randomNumber(), subject, place, "13H30", Arrays.asList("AAAA@lamzone.com", "it@ufo.com"));
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ place =  "+place);
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ filterOption =  "+filterOption);
        DI.getMeetingRepository().createMeeting(meeting);
        //finish();
        ListMeetingActivity.navigate(this);
    }
    public int randomNumber(){
        return ThreadLocalRandom.current().nextInt(1, 13);
    }

    /**
     * Used to navigate to this activity
     * @param activity
     * @param filterOption2
     */
    public static void navigate(FragmentActivity activity, String filterOption2) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        intent.putExtra("filterOption", filterOption2);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
