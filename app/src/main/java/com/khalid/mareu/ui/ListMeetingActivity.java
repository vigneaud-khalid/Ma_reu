package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.khalid.mareu.R;
import com.khalid.mareu.databinding.ActivityListMeetingBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import butterknife.OnClick;


public class ListMeetingActivity extends AppCompatActivity {


    private ActivityListMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.appbar);
        Log.d("ListMeetingActivity", "llll");
    }

    @OnClick(R.id.add_meeting)
    void addMeeting() {
        AddMeetingActivity.navigate(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return  true;
    }
}
