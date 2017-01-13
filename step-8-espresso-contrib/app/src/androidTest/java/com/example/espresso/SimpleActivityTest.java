package com.example.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

/**
 * Created by quanlt on 09/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleActivityTest {
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(SimpleActivity.class);

    @Test
    public void testOnSpinnerItemClickShouldShowCorrectHelloMessage() {
        fail("Haven't implemented yet");
    }
}