package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.repository.MeetingRepository;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;

    public MeetingRepository getmRep() {
        return mRep;
    }

    private MeetingRepository mRep;
    private MeetingFragment mMeetingFragment = new MeetingFragment();
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
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, mMeetingFragment)
                    .commit();
        }
        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addMeeting();  }
        });
    }

    public void chooseDate(){
        int selectedYear = 2022;
        int selectedMonth = 6;
        int selectedDayOfMonth = 21;
        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                dateFilter = ""+(monthOfYear + 1)+dayOfMonth;
                setDateFilter(dateFilter);
                filterOnDate(dateFilter);
            }
        };
        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }

    void addMeeting() {
        AddMeetingActivity.navigate(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.no_filter:
                noFilter();
                return true;
            case R.id.filter_on_date:
                chooseDate();
                return true;
            case R.id.filter_on_place_Peach:
                filterOnPlace("Peach");
                return true;
            case R.id.filter_on_place_Room2:
                filterOnPlace("Room2");
                return true;
            case R.id.filter_on_place_Room3:
                filterOnPlace("Room3");
                return true;
            case R.id.filter_on_place_Kiwi:
                filterOnPlace("Kiwi");
                return true;
            case R.id.filter_on_place_Berry:
                filterOnPlace("Berry");
                return true;
            case R.id.filter_on_place_Cherry:
                filterOnPlace("Cherry");
                return true;
            case R.id.filter_on_place_Room7:
                filterOnPlace("Room7");
                return true;
            case R.id.filter_on_place_Room8:
                filterOnPlace("Room8");
                return true;
            case R.id.filter_on_place_Grape:
                filterOnPlace("Grape");
                return true;
            case R.id.filter_on_place_Room10:
                filterOnPlace("Room10");
                return true;
           default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void filterOnDate(String date){
        mRep.meetingsDateFilter(date);
        mMeetingFragment.initList();
    }

    public void noFilter(){
        mRep.meetingsNoFilter();
        mMeetingFragment.initList();
    }

    public void filterOnPlace(String room){
        mRep.meetingsPlaceFilter(room);
        mMeetingFragment.initList();
    }

    public void setDateFilter(String dateFilter) {
        this.dateFilter = dateFilter;
    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, ListMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}