package com.test.ui.common;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AppiumUtils {

    public static void sleep(int miliseconds) throws Exception {
        try{
            TimeUnit.MILLISECONDS.sleep(miliseconds);
        }catch(Exception e){
            throw new Exception("Pause between steps was interrupted", e);
        }
    }

    public static void waitUntilElementTextNotEmpty(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        ExpectedCondition elementIsNotEmpty = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    return !element.getText().isEmpty();
                }catch(NoSuchElementException e){
                    return false;
                }catch(StaleElementReferenceException f){
                    return false;
                }
            }
        };

        wait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .until(elementIsNotEmpty);
    }

    public static void waitUntilElementNotToBeEnable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        ExpectedCondition elementIsNotEmpty = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    return !element.isEnabled();
                }catch(NoSuchElementException e){
                    return false;
                }catch(StaleElementReferenceException f){
                    return false;
                }
            }
        };

        wait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .until(elementIsNotEmpty);
    }

    public static void waitUntilElementContainsText(WebDriver driver, WebElement element, String containText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        ExpectedCondition elementIsNotEmpty = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                try{
                    return !element.getText().contains(containText);
                }catch(NoSuchElementException e){
                    return false;
                }catch(StaleElementReferenceException f){
                    return false;
                }
            }
        };

        wait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .until(elementIsNotEmpty);
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofSeconds(500))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementInVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        wait.ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofSeconds(500))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static void hideKeyboard(AndroidDriver driver){
        if(driver.isKeyboardShown()) driver.hideKeyboard();
    }
}
