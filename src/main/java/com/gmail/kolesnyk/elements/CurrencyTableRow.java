package com.gmail.kolesnyk.elements;

import com.gmail.kolesnyk.data.CurrencyTableColumns;
import com.gmail.kolesnyk.driver.DriverProvider;
import com.gmail.kolesnyk.utils.PropertyHelper;
import com.gmail.kolesnyk.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CurrencyTableRow extends BaseElement {

    private final String rateLocatorTemplate = "//td[contains(@data-title,'%s')]//span[@class='ng-binding']";
    private final String lossLocatorTemplate = "//td[@data-title='%s']//span[contains(@class, 'other-bank-loss')]";

    @FindBy(xpath = "//td[contains(@data-ng-if,'PROVIDERS.COMMERCIAL')]")
    private WebElement alternativeRateElement;

    public CurrencyTableRow(WebElement element) {
        super(element);
    }

    public String getBankRate(CurrencyTableColumns bank) {
        By locator = By.xpath(String.format(rateLocatorTemplate, bank.getLocator()));
        WaitHelper.waitForElementPresence(locator, PropertyHelper.getConf().elementTimeout());
        WebElement rate = DriverProvider.getDriver().findElement(locator);
        return rate.getText().trim();
    }

    public String getOtherBankLoss(CurrencyTableColumns bank) {
        By locator = By.xpath(String.format(lossLocatorTemplate, bank.getLocator()));
        WaitHelper.waitForElementPresence(locator, PropertyHelper.getConf().elementTimeout());
        WebElement rate = DriverProvider.getDriver().findElement(locator);
        String loss = rate.getText().trim();
        return loss.substring(1, loss.length() - 1);
    }

    public String getAlternativeRate() {
        return alternativeRateElement.getText().trim();
    }
}
