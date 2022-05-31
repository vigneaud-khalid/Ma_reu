package com.khalid.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.khalid.mareu.utils.FindPosition.atPosition;
import static com.khalid.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.khalid.mareu.ui.AddMeetingActivity;
import com.khalid.mareu.ui.ListMeetingActivity;
import com.khalid.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import android.widget.DatePicker;
import android.widget.TimePicker;


/**
 * Created by ordinateur _ Khalid _  on 17/05/2022.
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    private static int ITEMS_COUNT = 6;
    private static int itemsCount = 0;
    private ListMeetingActivity mActivity;

    @Rule
    public IntentsTestRule<ListMeetingActivity> mActivityRule =
            new IntentsTestRule(ListMeetingActivity.class);
    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**²
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(1)));
    }


    /**
     * When we select a place, PlaceFilter should display the meetings whose place matches with the chosen place
     */
    @Test
    public void myMeetingList_placeFilterMenu_shouldDisplayMeetings() {
        // When perform a click on filterMenu
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        // When perform a click on place Kiwi
        onView(withText("place: Kiwi"))
                .perform(click());
        // Then : only one item should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(1));
        // Then : Meeting 33 should be displayed
        //onView(withId(R.id.list_meetings)).check(matches(atPosition(0, withText("Meeting 33"))));
    }

    /**
     * When we select a date, DateFilter should display the meetings whose date matches with the chosen date
     */
    @Test
    public void myMeetingList_dateFilterMenu_shouldDisplayMeetings() {
        // When perform a click on filterMenu
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        // When perform a click on datefilter
        onView(withText("choose a date"))
                .perform(click());
        // We choose the date July 28:
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022, 7, 28));
        onView(withId(android.R.id.button1)).perform(click());
        // Then : only one item should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(1));
        // Then : Réunion E  should be displayed
        //onView(withId(R.id.list_meetings)).check(matches(atPosition(0, withText("Réunion E"))));
    }

    /**
     * When we select noFilter, noFilter should display all meetings
     */
    @Test
    public void myMeetingList_noFilterMenu_shouldDisplayAllMeetings() {
        // Given : the number of items is known
        itemsCount = mActivity.getmRep().getAllMeetings().size();
        // When perform a click on filterMenu
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        // When perform a click on noFilter
        onView(ViewMatchers.withText("No filter"))
                .perform(click());
        // Then : all items should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(itemsCount));
    }

    /**
     * When we click an icon addMeeting, AddMeetingActivity is opened
     */
    @Test
    public void myMeetingList_addMeeting_shouldOpenAddMeetingActivity() {
        // When perform a click on icon addMeeting
        onView(ViewMatchers.withId(R.id.add_meeting))
                .perform(click());
        // Then : We open AddMeetingActivity
        intended(hasComponent(AddMeetingActivity.class.getName()));
    }

    /**
     * When we add a new meeting, one more meeting should be displayed
     */
    @Test
    public void myMeetingList_addMeeting_shouldDisplayOneMoreMeeting() {
        // Given : the number of items is known
        itemsCount = mActivity.getmRep().getMeetings().size();
        // When perform a click on icon addMeeting
        onView(ViewMatchers.withId(R.id.add_meeting)).perform(click());
        //We fill the fields
        onView(ViewMatchers.withId(R.id.subjectField)).perform(replaceText("SuperBall"));
        onView(ViewMatchers.withId(R.id.autocomplete_place)).perform(replaceText("Berry"));
        onView(ViewMatchers.withId(R.id.autocomplete_attendees)).perform(replaceText("\"igor@lamzone.com\", \"ali@lamzone.com\""));
        onView(ViewMatchers.withId(R.id.onDateSet)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022, 11, 22));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.onTimeSet)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 30));
        onView(withId(android.R.id.button1)).perform(click());
        // When perform a click on createMeeting
        onView(ViewMatchers.withId(R.id.confirm_add_button))
                .perform(click());
        // Then : the number of displayed elements is one more
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(itemsCount+1));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        // Given : the number of elements is known
        itemsCount = mActivity.getmRep().getMeetings().size();
        // We ensure that the number of elements is correct
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(itemsCount));
        // When perform a click on a delete icon and remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0 , new DeleteViewAction()));
        // Then : the number of element is one less
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(itemsCount-1));
    }
}
