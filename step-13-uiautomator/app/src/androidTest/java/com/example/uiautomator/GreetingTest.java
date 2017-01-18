package com.example.uiautomator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.view.MotionEvent;
import android.widget.LinearLayout;
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
public class GreetingTest {

    private static final String PACKAGE_NAME = "com.example.uiautomator";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int WAIT_TIMEOUT = 2000;
    private UiDevice mUiDevice;

    @Before
    public void setUp() throws Exception {
        mUiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mUiDevice.pressHome();
        final String launcherPackage = mUiDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mUiDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        Context context = InstrumentationRegistry.getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mUiDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testGreeting() throws Exception {
        mUiDevice.findObject(By.res("com.example.uiautomator:id/edit_name")).clear();
        mUiDevice.findObject(By.res("com.example.uiautomator:id/edit_name")).setText("Obama");
        mUiDevice.findObject(new UiSelector().text("GREETING")).click();
        UiObject greetingText = mUiDevice.findObject(new UiSelector().description("Greeting text"));
        assertEquals("Hello Obama!", greetingText.getText());
    }

    @Test
    public void testListView() throws Exception {
        //swipe to the second tab
        mUiDevice.findObject(By.res("com.example.uiautomator:id/viewpager")).swipe(Direction.LEFT, 1f);
        //find the listview
        UiObject listView = mUiDevice.findObject(new UiSelector()
                .resourceId("com.example.uiautomator:id/list_country"));
        //find madagascar
        UiObject madagascar = listView.getChild(new UiSelector().className(LinearLayout.class).instance(3));
        //madagascar should have 2 child
        assertEquals(3, madagascar.getChildCount());
        //Madagascar text should be Madagascar
        assertEquals("Madagascar", madagascar.getChild(new UiSelector().className(TextView.class)).getText());
    }

    @Test
    public void testScrollListView() throws Exception {
        mUiDevice.findObject(By.res("com.example.uiautomator:id/viewpager")).swipe(Direction.LEFT, 1f);
        UiScrollable countries = new UiScrollable(new UiSelector().resourceId("com.example.uiautomator:id/list_country"));
        UiObject azerbaijan = countries.getChildByText(new UiSelector().className(TextView.class), "Azerbaijan");
        assertNotNull(azerbaijan);
    }

    @Test
    public void testMultiTouch() throws Exception {
        mUiDevice.findObject(By.descContains("Open navigation drawer")).click();
        mUiDevice.wait(Until
                .hasObject(By.res("com.example.uiautomator:id/design_menu_item_text")), WAIT_TIMEOUT);
        mUiDevice.findObject(By.res("com.example.uiautomator:id/design_menu_item_text")).click();
        mUiDevice.wait(Until
                .hasObject(By.res("com.example.uiautomator:id/text_touch_count")), WAIT_TIMEOUT);
        UiObject touchLayout = mUiDevice
                .findObject(new UiSelector().resourceId("com.example.uiautomator:id/activity_multi_touch"));
        touchLayout.performTwoPointerGesture(new Point(200, 200),
                new Point(300, 200), new Point(100, 200), new Point(600, 200), 10);
        UiObject2 shit = mUiDevice
                .findObject(By.res("com.example.uiautomator:id/text_touch_count"));
        assertEquals("2", shit.getText());
        MotionEvent.PointerCoords pc1 = new MotionEvent.PointerCoords();
        pc1.x = 200;
        pc1.y = 200;
        pc1.pressure = 1;
        pc1.size = 10;
        MotionEvent.PointerCoords pc2 = new MotionEvent.PointerCoords();
        pc2.x = 300;
        pc2.y = 300;
        pc2.pressure = 1;
        pc2.size = 10;
        MotionEvent.PointerCoords pc3 = new MotionEvent.PointerCoords();
        pc3.x = 400;
        pc3.y = 400;
        pc3.pressure = 1;
        pc3.size = 10;
        MotionEvent.PointerCoords pc4 = new MotionEvent.PointerCoords();
        pc4.x = 500;
        pc4.y = 500;
        pc4.pressure = 1;
        pc4.size = 10;
        MotionEvent.PointerCoords[] coords1 = new MotionEvent.PointerCoords[1];
        coords1[0] = pc1;
        MotionEvent.PointerCoords[] coords2 = new MotionEvent.PointerCoords[1];
        coords2[0] = pc2;
        MotionEvent.PointerCoords[] coords3 = new MotionEvent.PointerCoords[2];
        coords3[0] = pc3;
        coords3[1] = pc3;
        touchLayout.performMultiPointerGesture(coords1, coords2, coords3);
        assertEquals("5", shit.getText());

    }
}





















