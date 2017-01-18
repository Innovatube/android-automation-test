package com.example.uiautomator;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by quanlt on 16/01/2017.
 */

@RunWith(AndroidJUnit4.class)
public class NoteAppTest {
    private String PACKAGE_NAME = "com.example.android.testing.notes";
    private int LAUNCH_TIMEOUT = 5000;
    private int WAIT_TIMEOUT = 4000;
    private UiDevice mUiDevice;

    @Before
    public void setUp() throws Exception {
        mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mUiDevice.pressHome();
        final String launcherPackage = mUiDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        //get context
        Context context = InstrumentationRegistry.getContext();
        //create target package name
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        //clear all instance
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //open app
        context.startActivity(intent);
        //wait until applications is started
        mUiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testAddNote() throws Exception {
        UiObject2 object2 = mUiDevice.findObject(By
                .res("com.example.android.testing.notes:id/notes_list"));
        mUiDevice.wait(Until.hasObject(By.res("com.example.android.testing.notes:id/notes_list")), WAIT_TIMEOUT);
        //get current visible note
        int currentNote = object2.findObjects(By.clazz(FrameLayout.class)).size();
        //click on add note button
        mUiDevice.findObject(By.res("com.example.android.testing.notes:id/fab_add_notes"))
                .click();
        //wait until add note screen is shown up
        mUiDevice.wait(Until.hasObject(By
                        .res("com.example.android.testing.notes:id/add_note_title"))
                , WAIT_TIMEOUT);
        //set title
        mUiDevice.findObject(By.res("com.example.android.testing.notes:id/add_note_title"))
                .setText("Go to the cinema");
        //set description
        mUiDevice.findObject(By.res("com.example.android.testing.notes:id/add_note_description"))
                .setText("Your name is one of the best anime. " +
                        "Call your girl friend, and take her to the cinema.");
        //press back
        mUiDevice.pressBack();
        //save note
        mUiDevice.findObject(By.res("com.example.android.testing.notes:id/fab_add_notes")).click();
        //wait until a note list is shown up
        mUiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Go to the cinema")), WAIT_TIMEOUT);
        //the number of note should be increase by 1
        UiCollection collection = new UiCollection(new UiSelector()
                .resourceId("com.example.android.testing.notes:id/notes_list")
                .childSelector(new UiSelector().className(FrameLayout.class)));
        assertEquals(currentNote + 1, collection.getChildCount());
    }

    @Test
    public void testOpenNote() throws Exception {
        mUiDevice.wait(Until.hasObject(By.res("com.example.android.testing.notes:id/notes_list")), WAIT_TIMEOUT);
        UiObject firstNote = mUiDevice.findObject(new UiSelector()
                .resourceId("com.example.android.testing.notes:id/notes_list")
                .childSelector(new UiSelector()
                        .className(FrameLayout.class).instance(0)));
        String title = firstNote.getChild(new UiSelector().className(TextView.class).instance(0)).getText();
        String description = firstNote.getChild(new UiSelector().className(TextView.class).instance(1)).getText();

        firstNote.click();
        mUiDevice.wait(Until.hasObject(By.res("com.example.android.testing.notes:id/note_detail_title")), WAIT_TIMEOUT);
        assertEquals(title, mUiDevice.findObject(By.res("com.example.android.testing.notes:id/note_detail_title")).getText());
        assertEquals(description, mUiDevice.findObject(By.res("com.example.android.testing.notes:id/note_detail_description")).getText());
    }
}
