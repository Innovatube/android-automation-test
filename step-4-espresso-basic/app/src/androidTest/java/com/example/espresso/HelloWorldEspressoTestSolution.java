package com.example.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class HelloWorldEspressoTestSolution {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void listGoesOverTheFold() {
        Espresso.onView(ViewMatchers.withText("Hello World!"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testFindItemById() {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.text_greeting), withText("Hello World 2!")))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(Matchers.allOf(ViewMatchers.withId(R.id.text_greeting), Matchers.not(withText("Hello World!"))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
