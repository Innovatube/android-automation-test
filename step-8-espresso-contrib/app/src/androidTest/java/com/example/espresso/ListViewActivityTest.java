package com.example.espresso;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.espresso.model.Country;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.fail;

/**
 * Created by quanlt on 10/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ListViewActivityTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(ListViewActivity.class);

    @Test
    public void testListViewOnClick() throws Exception {
        //click on Mexico, then verify content of textview with id text_selected is 'Mexico'
        //click on Angola's flag
        fail("Haven't implemented yet");
    }

    private Matcher<Object> withName(final String name) {
        return new BoundedMatcher<Object, Country>(Country.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with name :" + name);
            }

            @Override
            protected boolean matchesSafely(Country item) {
                return item.name.equals(name);
            }
        };
    }
}
