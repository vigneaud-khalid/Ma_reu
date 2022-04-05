package com.khalid.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.service.MeetingApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {


   /* @BindView(R.id.create)
    MaterialButton addButton;

    private MeetingApiService mApiService;
    private String mMeetingImage;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       /*  setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getMeetingApiService();
        init();*/
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mMeetingImage = randomImage();
        Glide.with(this).load(mMeetingImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mMeetingImage

        );
        mApiService.createMeeting(meeting);
        finish();
    }
    */

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
