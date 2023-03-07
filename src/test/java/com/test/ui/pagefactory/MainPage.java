package com.test.ui.pagefactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage {

    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    private AndroidDriver driver;

    public MainPage(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="id")
    public WebElement idElement;

    @AndroidFindBy(id="pw")
    public WebElement pwElement;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().resourceIdMatches(\".*btnLogin.*\"))")
    public WebElement loginBtn;

    public void login(String id, String pw){
        idElement.sendKeys(id);
        pwElement.sendKeys(pw);
        loginBtn.click();
    }
}
