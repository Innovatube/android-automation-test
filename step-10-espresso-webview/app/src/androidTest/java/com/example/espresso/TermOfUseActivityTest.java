package com.example.espresso;

import android.content.Intent;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webKeys;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by quanlt on 12/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class TermOfUseActivityTest {
    @Rule
    public ActivityTestRule<TermOfUseActivity> mActivityTestRule =
            new ActivityTestRule<TermOfUseActivity>(TermOfUseActivity.class, false, false);


    @Test
    public void testTermOfUse() throws Exception {
        mActivityTestRule.launchActivity(null);
        onWebView().withElement(findElement(Locator.NAME,"title"))
                .check(webMatches(getText(),
                        containsString("Github Terms and Conditions of Service")));
    }

    @Test
    public void testAcceptAgreement() throws Exception {
        Intent intent = new Intent();
        intent.putExtra(TermOfUseActivity.ARG_FILE, "agreement.html");
        mActivityTestRule.launchActivity(intent);
        onWebView().withElement(findElement(Locator.NAME,"name"))
                .perform(clearElement())
                .perform(webKeys("Liz"))
                .withElement(findElement(Locator.NAME,"submit"))
                .perform(webClick())
                .withElement(findElement(Locator.ID,"instruction"))
                .check(webMatches(DriverAtoms.getText(),
                        Matchers.containsString("Please accept the agreement")));

        onWebView().withElement(findElement(Locator.NAME,"name"))
                .perform(clearElement())
                .perform(webKeys("Liz"))
                .withElement(findElement(Locator.NAME,"agree"))
                .perform(webClick())
                .withElement(findElement(Locator.NAME,"submit"))
                .perform(webClick())
                .withElement(findElement(Locator.ID,"instruction"))
                .check(webMatches(DriverAtoms.getText(), Matchers.containsString("Thank you Liz!")));
    }

}
