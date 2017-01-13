package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by quanlt on 11/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testButtonPosition(){
        Espresso.onView(ViewMatchers.withId(R.id.button_greeting))
                .check(isAbove(withId(R.id.text_greeting_unique)));
    }

    @Test
    public void testNavigationDrawer() throws Exception {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed()));
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen()));
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.nav_list));
        onView(allOf(isAssignableFrom(TextView.class), isDescendantOfA(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("ListView")));
    }

    @Test
    public void testOverflowMenu() throws Exception {
        openActionBarOverflowOrOptionsMenu(mActivityTestRule.getActivity());
        onView(withText("ListView")).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), isDescendantOfA(isAssignableFrom(Toolbar.class))))
                .check(matches(withText("ListView")));
    }

    @Test
    public void testSetBod() throws Exception {
        onView(withId(R.id.edit_bod)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(1993, 10, 1));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_bod)).check(matches(withText("1/10/1993")));
    }
}
