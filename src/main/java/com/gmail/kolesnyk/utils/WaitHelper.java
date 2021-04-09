package com.gmail.kolesnyk.utils;

import com.gmail.kolesnyk.driver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    public static void waitForElementPresence(WebElement parentElement, By elementLocator, int timeout) {
        new WebDriverWait(DriverProvider.getDriver(), timeout)
                .until((ExpectedCondition<Boolean>) d -> parentElement.findElements(elementLocator).size() > 0);
    }

    public static void waitForElementPresence(WebElement element, int timeout) {
        new WebDriverWait(DriverProvider.getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementPresence(By locator, int timeout) {
        new WebDriverWait(DriverProvider.getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementStayInvisible(WebElement element, int timeout) {
        new WebDriverWait(DriverProvider.getDriver(), timeout)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementHasText(WebElement element, int timeout) {
        new WebDriverWait(DriverProvider.getDriver(), timeout)
                .until((ExpectedCondition<Boolean>) d -> !element.getText().isEmpty());
    }
}
