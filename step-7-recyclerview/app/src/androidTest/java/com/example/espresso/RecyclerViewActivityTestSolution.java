package com.example.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.espresso.CharacterAdapter.CharacterViewHolder;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(RecyclerViewActivity.class);

    @Test
    public void testScrollToHolder() throws Exception {
        onView(withId(R.id.recycler_character))
                .perform(RecyclerViewActions.scrollToHolder(hasName("JYN ERSO")), click());
        onView(withId(R.id.text_role)).check(matches(isDisplayed()));
    }

    @Test
    public void testScrollToPosition() throws Exception {
        onView(withId(R.id.recycler_character))
                .perform(RecyclerViewActions.scrollToPosition(10), click());
    }

    @Test
    public void testActionOnHolderItem() throws Exception {
        onView(withId(R.id.recycler_character))
                .perform(RecyclerViewActions.actionOnHolderItem(hasName("BAZE MALBUS"), click()));
    }

    @Test
    public void testActionOnItem() throws Exception {
        onView(withId(R.id.recycler_character))
                .perform(RecyclerViewActions.actionOnItem(allOf(hasDescendant(withText("MOROFF")))
                        , click()));
    }

    @Test
    public void testActionOnItemAtPosition() throws Exception {
        onView(withId(R.id.recycler_character))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
        onView(withId(R.id.text_role)).check(matches(isDisplayed()));
    }


    private Matcher<RecyclerView.ViewHolder> hasName(final String name) {
        return new BoundedMatcher<RecyclerView.ViewHolder, CharacterViewHolder>(CharacterViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has name :" + name);
            }

            @Override
            protected boolean matchesSafely(CharacterViewHolder item) {
                return item.mNameTextView.getText().toString().equals(name);
            }
        };
    }
}
