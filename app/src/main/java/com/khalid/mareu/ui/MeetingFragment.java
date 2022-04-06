package com.khalid.mareu.ui;
import android.util.Log;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalid.mareu.R;
import com.khalid.mareu.di.DI;
import com.khalid.mareu.events.DeleteMeetingEvent;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.repository.MeetingRepository;
import com.khalid.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MeetingFragment extends Fragment {

    private MeetingRepository mRep;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;

    /**
     * Create and return a new instance
     * @return @{@link MeetingFragment}
     */
    public static MeetingFragment newInstance() {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("rrrr","MeetingFragment");
        super.onCreate(savedInstanceState);
        mRep = DI.getMeetingRepository();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        Log.d("rrrr","createview");
        return view;
    }
    /**
     * Init the List of meetings
     */
    private void initList() {
        mMeetings = mRep.getMeetings();
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mRep.deleteMeeting(event.meeting);
        initList();
    }
}
