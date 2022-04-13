package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.request.transition.Transition;
import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.OnClick;


public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;
    // Transition.ViewAdapter mViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.appbar);
        Log.d("rrrr", "ListMeetingActivity");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, MeetingFragment.class, null)
                    .commit();
        }
        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeeting();
            }
        });
    }
        void addMeeting() {
        Log.d("rrrr", "ListMeetingActivity ---  onclick");
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
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()==R.id.filter_on_date) {

        }
        return super.onOptionsItemSelected(item);
    }
}
