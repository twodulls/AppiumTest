package com.test.ui.common;

import com.test.ui.testdata.TestData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BaseTest {

    public static AppiumDriverLocalService service;
    public AndroidDriver aliceDriver, bobDriver;
    public DesiredCapabilities caps;
    public static String PAGEID, PAGEPW;

    @BeforeSuite
    public void beforeSuite() throws FileNotFoundException {
        InputStream input = new FileInputStream(new File("src/test/resources/data.yml"));
        Yaml yaml = new Yaml();
        TestData data = yaml.loadAs(input, TestData.class);

        PAGEID = data.getId();
        PAGEPW = data.getPw();

        //appium service start
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.withIPAddress("127.0.0.1").usingPort(4723);
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.withBasePath("/wd/hub");
        service.start();

        if(service ==  null || !service.isRunning()){
            throw new RuntimeException("appium server is not started..");
        }
    }

    @AfterSuite
    public void afterSuite(){
        if(service != null) service.stop();
    }

    public DesiredCapabilities setCapability(String udid){
        caps = new DesiredCapabilities();
        caps.setCapability("udid", udid);
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.test.application");
        caps.setCapability("appActivity", "com.test.application.activity");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("autoGrantPermissions", "true");
        return caps;
    }
}
