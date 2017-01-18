import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by quanlt on 17/01/2017.
 */
public class AddNoteTest {
    private AndroidDriver driver;
    private File noteApk = new File("src/main/resources/note-app-debug.apk");

    @Before
    public void setUp() throws Exception {
        //declare device's capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //device name
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        //path to your apk
        capabilities.setCapability(MobileCapabilityType.APP, noteApk.getAbsolutePath());
        //platform name
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //initialize driver with url and capabilities, if you don't change parameter, default ip is 127.0.0.01
        //and the default host is 4723
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testAddNote() throws Exception {
        //initialize a wait instance
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait until notes_list is presented
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout")));
        //count the number of notes
        int currentNumOfNote = driver.findElement(By.id("notes_list"))
                .findElements(By.className("android.widget.FrameLayout"))
                .size();
        //find add note button and click
        driver.findElement(By.id("fab_add_notes")).click();
        //wait until add_note_tile is visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_note_title")));
        //set note title
        driver.findElement(By.id("add_note_title")).sendKeys("Buy Final Fantasy XV");
        //note description
        driver.findElement(By.id("add_note_description")).sendKeys("Blah blah");
        //save
        driver.findElement(By.id("fab_add_notes")).click();
        //wait until the element contains 'Final Fantasy XV' is visible
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Final Fantasy XV')]")));
        //count the number of notes
        int newNumOfNote = driver.findElement(By.id("notes_list"))
                .findElements(By.className("android.widget.FrameLayout"))
                .size();
        assertEquals(currentNumOfNote+1, newNumOfNote);
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();

    }
}
