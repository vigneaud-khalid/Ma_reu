package com.khalid.mareu.ui;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.khalid.mareu.R;
import com.khalid.mareu.events.DeleteMeetingEvent;
import com.khalid.mareu.model.Meeting;
import com.khalid.mareu.ui.placeholder.PlaceholderContent.PlaceholderItem;
// import com.khalid.mareu.ui.databinding.FragmentItemBinding;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("rrrr","MyMeetingRecyclerViewAdapter");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mMeetingAvatar.setImageDrawable(holder.mMeetingAvatar.getContext().getDrawable(getAvatar(meeting.getAvatarColor())));
        holder.mMeetingSubject.setText(meeting.getSubject()+" - "+meeting.getTime()+ " - "+meeting.getPlace());
        holder.mMeetingAttendees.setText((meeting.getAttendees()).toString());
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_round_color_circle)
        public ImageView mMeetingAvatar;
        @BindView(R.id.meeting_description)
        public TextView mMeetingSubject;
        @BindView(R.id.meeting_attendees)
        public TextView mMeetingAttendees;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public int getAvatar(int num){
        switch (num){
            case 1: return R.drawable.ic_baseline_circle_24_green;
            case 2: return R.drawable.ic_baseline_circle_24_pink;
            case 3: return R.drawable.ic_baseline_circle_24_yellow;
            case 4: return R.drawable.ic_baseline_circle_24_blue;
            case 5: return R.drawable.ic_baseline_circle_24_lightgreen;
            case 6: return R.drawable.ic_baseline_circle_24_red;
            case 7: return R.drawable.ic_baseline_circle_24_pastel;
            case 8: return R.drawable.ic_baseline_circle_24_lightgreen;
            case 9: return R.drawable.ic_baseline_circle_24_pastel;
            default:return R.drawable.ic_baseline_circle_24_red;
        }
    }
}
