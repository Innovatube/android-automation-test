package com.example.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PerformActionActivityTestSolution {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(PerformActionActivity.class);

    @Test
    public void testTypeText() throws Exception {
        onView(withId(R.id.edit_animal)).perform(typeText("Pikachu"), pressBack());
    }

    @Test
    public void testClick() throws Exception {
        onView(withId(R.id.button_buy)).perform(click());
    }

    @Test
    public void testScroll() throws Exception {
        onView(withText("Ant")).perform(scrollTo(), click());
    }


}
