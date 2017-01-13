package com.example.espresso;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by quanlt on 12/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileActivityTestSolution {
    @Rule
    public IntentsTestRule mActivityRule = new IntentsTestRule(ProfileActivity.class);

    @Test
    public void testSelectContact() throws Exception {
        Intent intent = new Intent();
        //put 'Obama' to intent
        intent.putExtra(SignUpActivity.EXTRA_FULLNAME, "Obama");
        //create a result
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
        //when and intent to package com.example.espresso is seen, response with activityResult
        intending(toPackage("com.example.espresso")).respondWith(activityResult);
        onView(withId(R.id.text_user_info)).check(matches(withText("Please Sign Up!")));
        onView(withId(R.id.button_sign_up)).perform(click());
        //verify that when user click on button_sign_up, an intent to package com.example.espresso is fired
        intended(toPackage("com.example.espresso"));
        //verify content of text_user_info
        onView(withId(R.id.text_user_info)).check(matches(withText("Hello Obama")));
    }



    @Test
    public void testSelectImage() throws Exception {
        Intent intent = new Intent();
        intent.setData(Uri.parse("android.resource://com.example.espresso/drawable/ic_menu_tos"));
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, intent);
        intending(allOf(hasAction(Intent.ACTION_PICK), hasData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)))
                .respondWith(activityResult);
        onView(withId(R.id.image_avatar)).check(matches(not(hasDrawable())));
        onView(withId(R.id.button_take_image)).perform(click());
        onView(withId(R.id.image_avatar)).check(matches(hasDrawable()));
    }

    private Matcher<? super View> hasDrawable() {
        return new BoundedMatcher<Object, View>(View.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof ImageView) {
                    return ((ImageView) item).getDrawable() != null;
                }
                return false;
            }
        };
    }

}
