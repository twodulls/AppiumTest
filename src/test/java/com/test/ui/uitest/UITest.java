package com.test.ui.uitest;

import com.test.ui.common.BaseTest;
import com.test.ui.pagefactory.MainPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class UITest extends BaseTest {

    public MainPage mainPage;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        aliceDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), setCapability("UDID XXX"));
        bobDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), setCapability("UDID XXX"));

        aliceDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        bobDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /*
        PageFactory 객체 생성
         */
        mainPage = new MainPage(aliceDriver);
    }

    @AfterClass
    public void tearDown(){
        /*
        assertAll
         */
        if(aliceDriver != null) aliceDriver.quit();
        if(bobDriver != null) bobDriver.quit();

    }

    @Test
    public void test(){
        /*
        1. 동작 진행
        2. assert
         */
        mainPage.login(PAGEID, PAGEPW);
    }
}
