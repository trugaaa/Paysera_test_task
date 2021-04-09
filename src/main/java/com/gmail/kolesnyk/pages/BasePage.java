package com.gmail.kolesnyk.pages;

import com.gmail.kolesnyk.driver.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(DriverProvider.getDriver(), this);
    }
}
