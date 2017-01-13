package com.example.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class HelloWorldEspressoTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("Hello World!"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testFindItemById() {
        onView(allOf(withId(R.id.text_greeting), withText("Hello World 2!")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.text_greeting), not(withText("Hello World!"))))
                .check(matches(isDisplayed()));
    }

}
