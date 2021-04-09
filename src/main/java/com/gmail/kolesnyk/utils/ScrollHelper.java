package com.gmail.kolesnyk.utils;

import com.gmail.kolesnyk.driver.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ScrollHelper {

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
