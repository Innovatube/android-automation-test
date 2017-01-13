package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PerformActionActivityTest {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(PerformActionActivity.class);

    @Test
    public void testTypeText() throws Exception {
        fail("haven't implemented yet");
    }

    @Test
    public void testClickBuyButton() throws Exception {
        fail("haven't implemented yet");
    }

    @Test
    public void testScroll() throws Exception {
        Espresso.onView(ViewMatchers.withText("Ant")).
                perform(ViewActions.scrollTo(), ViewActions.click());
    }


}
