package com.innovatube.boilerplate;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.innovatube.boilerplate.ui.home.HomeActivity;
import com.innovatube.boilerplate.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by quanlt on 11/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTestWithoutDialog {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(HomeActivity.class);
    private IdlingResource idlingResource;

    @Before
    public void setUp() throws Exception {
        idlingResource = EspressoIdlingResource.getIdlingResource();
        registerIdlingResources(idlingResource);
    }

    @Test
    public void testSearchUserSuccess() throws Exception {
        onView(withId(R.id.rl_user)).check(matches(not(isDisplayed())));
        onView(withId(R.id.edit_username)).perform(typeText("google"), pressBack());
        onView(withId(R.id.button_search)).perform(click());
        onView(withId(R.id.rl_user)).check(matches(isDisplayed()));
        onView(withId(R.id.text_login)).check(matches(withText("google")));
    }

    @After
    public void tearDown() throws Exception {
        unregisterIdlingResources(idlingResource);
    }
}
