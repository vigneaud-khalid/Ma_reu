package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.repository.MeetingRepository;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import butterknife.OnClick;


public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;
    private MeetingRepository mRep;
    private MeetingFragment mMeetingFragment = new MeetingFragment();
    public String filter = "noFilter";
    public String filterOption;
    public String dateFilter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRep = DI.getMeetingRepository();
        setContentView(R.layout.activity_list_meeting);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.appbar);
        Log.d("rrrr", "ListMeetingActivity");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, mMeetingFragment)
                    .commit();
        }
        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                filter = getFilter();
                Log.d("rrrr", "ListMeetingActivity _ onCreate() _ onClick() _ filterOption  =  "+filter);

                addMeeting(filter);
            }
        });
    }

    public void chooseDate(){
        int selectedYear = 2022;
        int selectedMonth = 5;
        int selectedDayOfMonth = 02;
        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                EditText editTextDate = findViewById(R.id.date);
                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                dateFilter = monthOfYear+dayOfMonth+" ";
                // chooseTime();

            }
        };
        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }

    public void chooseTime(){
        boolean is24HView = true;
        int selectedHour = 10;
        int selectedMinute = 20;
        int lastSelectedHour =  0;
        int lastSelectedMinute = 0;

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                EditText editTextDate = findViewById(R.id.date);
                editTextDate.append(" "+hourOfDay + ":" + minute );
                //lastSelectedHour = hourOfDay;
                //lastSelectedMinute = minute;
                dateFilter += hourOfDay + ":" + minute;
            }
        };
        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour, lastSelectedMinute, is24HView);
        timePickerDialog.show();
    }


    void addMeeting(String filterOption) {
        AddMeetingActivity.navigate(this);
    }

    @OnClick(R.id.filter)
    void filter() {
        Log.d("rrrr", "ListMeetingActivity ---  onclick Filter");
        // todo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // if (item.getItemId()==R.id.filter_on_date) {      }
        switch (item.getItemId()) {
            case R.id.no_filter:
                filterOption = "noFilter";
                Log.d("rrrr", "ListMeetingActivity _ onOptionsItemSelected _ filterOption =  "+filterOption);
                noFilter();
                return true;
            case R.id.filter_on_date:
                Log.d("rrrr", "ListMeetingActivity _ onOptionsItemSelected _ filter_on_date_ dateFilter =  "+dateFilter);

                chooseDate();
                chooseTime();
                filterOnDate(dateFilter);
                return true;
            case R.id.filter_on_place_Peach:
                filterOption = "Peach";
                setFilter(filterOption);
                Log.d("rrrr", "ListMeetingActivity _ onOptionsItemSelected _ filterOption(Peach)  =  "+filterOption);

                filterOnPlace("Peach");
                return true;
            case R.id.filter_on_place_Room2:

                filterOnPlace("Room 2");
                return true;
            case R.id.filter_on_place_Room3:

                filterOnPlace("Room 3");
                return true;
            case R.id.filter_on_place_Kiwi:
                filterOption = "Kiwi";
                setFilter(filterOption);
                Log.d("rrrr", "ListMeetingActivity _ onOptionsItemSelected _ filterOption(Kiwi)  =  "+filterOption);

                filterOnPlace("Kiwi");
                return true;
            case R.id.filter_on_place_Berry:

                filterOnPlace("Berry");
                return true;
            case R.id.filter_on_place_Cherry:
                filterOnPlace("Cherry");
                return true;
            case R.id.filter_on_place_Room7:
                filterOnPlace("Room 7");
                return true;
            case R.id.filter_on_place_Room8:
                filterOnPlace("Room 8");
                return true;
            case R.id.filter_on_place_Grape:
                filterOnPlace("Grape");
                return true;
            case R.id.filter_on_place_Room10:
                filterOnPlace("Room 10");
                return true;
           default:
                return super.onOptionsItemSelected(item);
        }
    }

    //public String getFilter(){ Log.d("rrrr", "ListMeetingActivity _ getFilterOption()  =  "+filterOption);return filterOption ;}

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filterOption) {
        this.filter = filterOption;
    }

    public void filterOnDate(String date){
        // comment récupérer une String date
        //String date = "Jul06 10:30";
        mRep.meetingsDateFilter(date);
        mMeetingFragment.initList();
    }

    public void noFilter(){
        mRep.meetingsNoFilter();
        ListMeetingActivity.navigate(this);

    }

    public void filterOnPlace(String room){
        Log.d("rrrr", "ListMeetingActivity _ filterOnPlace _ filterOption(Peach)  =  "+filterOption);

        mRep.meetingsPlaceFilter(room);
        mMeetingFragment.initList();
        //ListMeetingActivity.navigate(this);
    }



    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, ListMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}