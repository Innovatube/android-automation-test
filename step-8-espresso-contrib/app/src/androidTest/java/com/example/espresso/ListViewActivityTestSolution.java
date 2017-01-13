package com.example.espresso;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;

import com.example.espresso.model.Country;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ListViewActivityTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(ListViewActivity.class);

    @Test
    public void testListViewOnClick() throws Exception {
        onData(withName(startsWith("Mexico"))).perform(click());
        onView(withId(R.id.text_selected)).check(matches(withText("Mexico")));
        onData(withName(startsWith("Angol")))
                .onChildView(isAssignableFrom(ImageView.class))
                .perform(click());
    }

    private Matcher<Object> withName(final Matcher<String> textMatcher){
        return new BoundedMatcher<Object, Country>(Country.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                textMatcher.describeTo(description);

            }

            @Override
            protected boolean matchesSafely(Country item) {
                return textMatcher.matches(item.name);
            }
        };
    }
}
