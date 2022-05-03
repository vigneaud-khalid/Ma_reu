package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

        TextView textDate = findViewById(R.id.date);
        Date dateMeeting = new Date(0, 0, 1, 0, 0);

        MultiAutoCompleteTextView place = (MultiAutoCompleteTextView) findViewById(R.id.autocomplete_place);
        String[] places = getResources().getStringArray(R.array.places_array);
        ArrayAdapter<String> adapterPlaces =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, places);
        place.setAdapter(adapterPlaces);
        place.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    public void chooseDate(View view){
        int selectedYear = 2022;
        int selectedMonth = 5;
        int selectedDayOfMonth = 02;
        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                TextView textDate = findViewById(R.id.date);
                String day = ""+dayOfMonth;
                if ( dayOfMonth<10) {   day = "0" + day;   }
                String month = ""+monthOfYear;
                if ( monthOfYear<10) {   month = "0" + month;   }
                textDate.setText(day + "-" + (month + 1) + "-" + year);

            }
        };
        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }

    public void chooseTime(View view){
        boolean is24HView = true;
        int selectedHour = 10;
        int selectedMinute = 20;
        int lastSelectedHour =  0;
        int lastSelectedMinute = 0;

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView textDate = findViewById(R.id.date);
                String hour = ""+hourOfDay;
                if ( hourOfDay>0 && hourOfDay<10) {   hour = "0" + hour;   }
                String min = ""+minute;
                if ( minute<10) {   min = "0" + min;   }
                textDate.append(" "+hour + ":" + min );
                //lastSelectedHour = hourOfDay;
                //lastSelectedMinute = minute;
            }
        };
        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        timePickerDialog.show();
    }

    public void submitButtonHandler(View view) throws ParseException {
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ before interfering with the id");
        // récupérer l'id du  dernier meeting et l'incrémenter
        int numberMeetings = DI.getMeetingRepository().getMeetings().size();
        long firstId = DI.getMeetingRepository().getMeetings().get(0).getId();
        long lastId = DI.getMeetingRepository().getMeetings().get(numberMeetings-1).getId();
        long id = lastId++;
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ numberMeetings =  "+numberMeetings);
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ firstId =  "+firstId);
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ lastId =  "+lastId);
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ id =  "+id);

        EditText subjectEditText = (EditText) findViewById(R.id.subject);
        String subject = subjectEditText.getText().toString();
        // control of the field
        if (subject.isEmpty()) {
            subjectEditText.setError("YOU HAVE TO NAME THE SUBJECT !!!");
            return;
        }
        EditText placeEditText = (EditText) findViewById(R.id.autocomplete_place);
        String place = placeEditText.getText().toString();
        // control of the field
        if (place.isEmpty()) {
            placeEditText.setError("YOU HAVE TO NAME THE PLACE !!!");
            return;
        }

        TextView textDate = (TextView) findViewById(R.id.date);
        String dateRetrieved = textDate.getText().toString();
        String pattern = "dd-MM-yyyy hh:mm";
        Date date = new SimpleDateFormat(pattern).parse(dateRetrieved);

        // control of the field
        if (date == null || date.toString()=="") {
            textDate.setError("YOU HAVE TO DEFINE THE TIME !!!");
            return;
        }


        EditText attendeesEditText = (EditText) findViewById(R.id.autocomplete_attendees);
        List<String> attendees = (List<String>) Arrays.asList(attendeesEditText.getEditableText().toString().split(" "));
        Log.d("rrrr", "AddMeetingActivity _ submitButtonHandler _ attendees =  " + attendees);
        // control of the field
        if (attendees.isEmpty()) {
            attendeesEditText.setError("YOU HAVE TO NAME AT LEAST ONE ATTENDEE !!!");
            return;
        }

        // create a new meeting and add it to the list
        //Meeting meeting = new Meeting(12, randomNumber(), subject, place, new Date(), Arrays.asList("AAAA@lamzone.com", "it@ufo.com"));
        if (subject != "" && place != "" && date != null && !attendees.isEmpty()) {
            Meeting meeting = new Meeting(12, randomNumber(), subject, place, date, attendees);
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
