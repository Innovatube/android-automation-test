package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class PerformActionActivityTestSolution {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(PerformActionActivity.class);

    @Test
    public void testTypeText() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.edit_animal))
                .perform(ViewActions.typeText("Pikachu"), ViewActions.pressBack());
    }

    @Test
    public void testClickBuyButton() throws Exception {
        Espresso.onView(withId(R.id.button_buy))
                .perform(ViewActions.click());
    }

    @Test
    public void testScroll() throws Exception {
        Espresso.onView(withText("Ant"))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }


}
