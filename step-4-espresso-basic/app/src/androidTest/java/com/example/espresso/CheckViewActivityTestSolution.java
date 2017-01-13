package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CheckViewActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(CheckViewActivity.class);

    @Test
    public void testTextViewWithText() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.text_hello))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello TextView")));
    }

    @Test
    public void testButtonWithText() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.text_hello))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello Button")));
    }
}
