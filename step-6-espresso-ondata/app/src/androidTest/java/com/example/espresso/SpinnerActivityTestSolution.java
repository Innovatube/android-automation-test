package com.example.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class SpinnerActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(SpinnerActivity.class);

    @Test
    public void testOnSpinnerItemClickShouldShowHelloMessage() {
        onView(withId(R.id.spinner_simple)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Americano"))).perform(click());
        onView(withId(android.R.id.text1))
                .check(matches(withText(containsString("Americano"))));
    }
}
