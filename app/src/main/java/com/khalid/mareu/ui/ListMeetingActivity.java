package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.transition.Transition;
import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
    }

    @OnClick(R.id.add_meeting)
    void addMeeting() {
        Log.d("rrrr", "ListMeetingActivity ---  onclick");
        AddMeetingActivity.navigate(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return  true;
    }
}
