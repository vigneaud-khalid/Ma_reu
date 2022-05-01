package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.repository.MeetingRepository;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.OnClick;


public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;
    private MeetingRepository mRep;
    private MeetingFragment mMeetingFragment = new MeetingFragment();
    public String filter = "noFilter";
    public String filterOption;

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
                // filterOnDate();
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

    public void filterOnDate(){
        // todo
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