package com.gmail.kolesnyk.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CurrencyDropdown extends BaseElement {

    private final String currencyLocatorPattern = "//span[@data-ng-bind='currency' and text()='%s']";

    public CurrencyDropdown(WebElement element) {
        super(element);
    }

    @Step("Select country '{currency}'")
    public void selectCurrency(String currency) {
        By currencyLocator = getCurrencyLocator(currency);
        WebElement currencyElement = getComponentElement().findElement(currencyLocator);
        currencyElement.click();
    }

    private By getCurrencyLocator(String currency) {
        return By.xpath(String.format(currencyLocatorPattern, currency));
    }
}