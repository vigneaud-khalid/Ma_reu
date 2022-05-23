package com.khalid.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.khalid.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.khalid.mareu.ui.AddMeetingActivity;
import com.khalid.mareu.ui.ListMeetingActivity;
import com.khalid.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


/**
 * Created by ordinateur _ Khalid _  on 17/05/2022.
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    private static int ITEMS_COUNT = 6;
    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);
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
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 5
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click an icon addMeeeting, AddMeetingActivity is opened
     */
    @Test
    public void myMeetingList_addMeeeting_shouldOpenAddMeetingActivity() {
        // When perform a click on icon addMeeting
        onView(ViewMatchers.withId(R.id.add_meeting))
                .perform(click());
        // Then : We open AddMeetingActivity
        intended(hasComponent(AddMeetingActivity.class.getName()));
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
        onView(ViewMatchers.withId(R.id.filter_on_place_Kiwi))
                .perform(click());
        // Then : only one item should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(1));

        // Then : Réunion 33 should be displayed
        //onView(ViewMatchers.withId(R.id.list_meetings)).check(withId(3));
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
        onView(ViewMatchers.withId(R.id.filter_on_date))
                .perform(click());
        // We choose the date July 28:


        // Then : only one item should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(1));

        // Then : Réunion E  should be displayed
        //onView(ViewMatchers.withId(R.id.list_meetings)).check(withId(5));
    }

    /**
     * When we select noFilter, noFilter should display all meetings
     */
    @Test
    public void myMeetingList_noFilterMenu_shouldDisplayAllMeetings() {
        // When perform a click on filterMenu
        onView(ViewMatchers.withId(R.id.filter))
                .perform(click());
        // When perform a click on noFilter
        onView(ViewMatchers.withId(R.id.no_filter))
                .perform(click());
        // Then : 6 item should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(6));


        // Then : the 5 should be displayed
        //onView(ViewMatchers.withId(R.id.list_meetings)).check(withId(1 to 6));
    }

    /**
     * When we add a new meeting, one more meeting should be displayed
     */
    @Test
    public void myMeetingList_addMeeeting_shouldDisplayOneMoreMeeting() {
        // When perform a click on icon addMeeting
        onView(ViewMatchers.withId(R.id.add_meeting))
                .perform(click());
        //We fill the fields
        //onView(ViewMatchers.withId(R.id.subjectField))


        // When perform a click on createMeeting
        onView(ViewMatchers.withId(R.id.confirm_add_button))
                .perform(click());
        // Then : 7 meetings should be displayed
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(7));

        // Then : the 5 should be displayed
        //onView(ViewMatchers.withId(R.id.list_meetings)).check(withId(1 to 6));
    }




}
