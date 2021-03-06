package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

    static int lastSelectedHour =  9;
    static int lastSelectedMinute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        AutoCompleteTextView place = (AutoCompleteTextView) findViewById(R.id.autocomplete_place);
        String[] places = getResources().getStringArray(R.array.places_array);
        ArrayAdapter<String> adapterPlaces =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, places);
        place.setAdapter(adapterPlaces);

        MultiAutoCompleteTextView textView = (MultiAutoCompleteTextView) findViewById(R.id.autocomplete_attendees);
        String[] attendees = getResources().getStringArray(R.array.attendees_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendees);
        textView.setAdapter(adapter);
        textView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        TextView textDate = findViewById(R.id.dateDisplay);
        Date dateMeeting = new Date(0, 0, 1, 0, 0);
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
                TextView textDate = findViewById(R.id.dateDisplay);
                String day = ""+dayOfMonth;
                if ( dayOfMonth<10) {   day = "0" + day;   }
                String month = ""+(++monthOfYear);
                if ( monthOfYear<10) {   month = "0" + month;   }
                textDate.setText(day + "-" + (month) + "-" + year);
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

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView textDate = findViewById(R.id.dateDisplay);
                String hour = ""+hourOfDay;
                if ( hourOfDay>=0 && hourOfDay<10) {   hour = "0" + hour;   }
                String min = ""+minute;
                if ( minute<10) {   min = "0" + min;   }
                textDate.append("  "+hour + ":" + min );
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };
        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        timePickerDialog.show();
    }

    public void submitButtonHandler(View view) throws ParseException {
        // id incrementing
        int numberMeetings = DI.getMeetingRepository().getAllMeetings().size();
        long lastId = DI.getMeetingRepository().getAllMeetings().get(numberMeetings-1).getId();
        long id = lastId +1;

        EditText subjectEditText = (EditText) findViewById(R.id.subjectField);
        String subject = subjectEditText.getText().toString();
        // control of the field subject
        if (subject.isEmpty()) {
            subjectEditText.setError("You have to name the subject !");
            return;
        }
        EditText placeEditText = (EditText) findViewById(R.id.autocomplete_place);
        String place = placeEditText.getText().toString();
        // control of the field place
        if (place.isEmpty()) {
            placeEditText.setError("You have to name the place !");
            return;
        }

        EditText attendeesEditText = (EditText) findViewById(R.id.autocomplete_attendees);
        List<String> attendees = (List<String>) Arrays.asList(attendeesEditText.getEditableText().toString().split(" "));
        // control of the field
        if (attendees.isEmpty()) {
            attendeesEditText.setError("You have to name at least one attendee !");
            return;
        }

        TextView textDate = (TextView) findViewById(R.id.dateDisplay);
        String dateRetrieved = textDate.getText().toString();
        String pattern = "dd-MM-yyyy hh:mm";
        Date date = null;
        // control of the field date
        try { date = new SimpleDateFormat(pattern).parse(dateRetrieved); }
        catch (Exception e){ Toast.makeText(this,"You have to define the date & time !", Toast.LENGTH_LONG).show();
        }

        // create a new meeting and add it to the list
        if (subject != "" && place != "" && date != null && !attendees.isEmpty()) {
            Meeting meeting = new Meeting(id, randomNumber(), subject, place, date, attendees);
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