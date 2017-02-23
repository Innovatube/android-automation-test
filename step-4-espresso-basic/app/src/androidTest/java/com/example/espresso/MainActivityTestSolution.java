package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

/**
 * Created by quanlt on 12/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testClickButtonAndVerifyTextView() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.button_greeting));
        Espresso.onView(ViewMatchers.withId(R.id.button_greeting)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.text_greeting_unique)).check(ViewAssertions.matches(ViewMatchers.withText("Hello there")));

    }

    @Test
    public void testTypeTextThenClickButtonAndVerifyTextView() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.edit_name))
                .perform(ViewActions.typeText("Shane"), ViewActions.pressBack());
        Espresso.onView(ViewMatchers.withId(R.id.button_greeting))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.text_greeting_unique))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello Shane")));
    }
}
