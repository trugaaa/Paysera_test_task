package com.gmail.kolesnyk.pages;

import com.gmail.kolesnyk.data.CurrencyTableColumns;
import com.gmail.kolesnyk.elements.CountryDropdown;
import com.gmail.kolesnyk.elements.CurrencyDropdown;
import com.gmail.kolesnyk.elements.CurrencyTable;
import com.gmail.kolesnyk.utils.PropertyHelper;
import com.gmail.kolesnyk.utils.ScrollHelper;
import com.gmail.kolesnyk.utils.WaitHelper;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

@Slf4j
public class CurrencyCalculatorPage extends BasePage {

    @FindBy(xpath = "//input[contains(@data-ng-model,'from_amount')]")
    private WebElement sellField;

    @FindBy(xpath = "//div[contains(@data-ng-model,'filter.from')]//span[@class='ng-binding ng-scope']")
    private WebElement sellCurrencyTextElement;

    @FindBy(xpath = "//input[contains(@data-ng-model,'to_amount')]")
    private WebElement buyField;

    @FindBy(xpath = "//div[contains(@data-ng-model,'filter.to')]//span[@class='ng-binding ng-scope']")
    private WebElement buyCurrencyTextElement;

    @FindBy(css = ".js-localization-popover")
    private WebElement selectCountryIconElement;

    @FindBy(css = "div.popover-content")
    private WebElement selectCountryPopup;

    @FindBy(xpath = "(//td[contains(@data-ng-if,'PROVIDERS.OFFICIAL]')])[1]")
    private WebElement officialRateUSAElement;

    @FindBy(css = "table.transformable-table")
    private WebElement currencyTableElement;

    @FindBy(xpath = "//div[contains(@data-ng-model,'filter.from')]//ul[contains(@class, 'ui-select-dropdown')]")
    private WebElement sellCurrencyDropdown;

    @FindBy(xpath = "//div[contains(@data-ng-model,'filter.to')]//ul[contains(@class, 'ui-select-dropdown')]")
    private WebElement buyCurrencyDropdown;

    @FindBy(css = "div[data-ng-show='currencyExchangeVM.loading']")
    private WebElement pageLoaderElement;

    @Step("Set sell amount: {amount}")
    public void setSellAmount(String amount) {
        ScrollHelper.scrollToElement(sellField);
        sellField.clear();
        sellField.sendKeys(amount);
    }

    public void interSellAmount(String amount) {
        setSellAmount(amount);
        sellField.sendKeys(Keys.ENTER);
        waitForPageLoading();
    }

    @Step("Click on Sell amount field")
    public void clickOnSellAmount() {
        ScrollHelper.scrollToElement(sellField);
        sellField.click();
    }

    public String getSellAmount() {
        return sellField.getAttribute("value");
    }

    @Step("Set buy amount: {amount}")
    public void setBuyAmount(String amount) {
        ScrollHelper.scrollToElement(buyField);
        WaitHelper.waitForElementStayInvisible(pageLoaderElement, PropertyHelper.getConf().pageLoadTimeout());
        buyField.clear();
        buyField.sendKeys(amount);
    }

    @Step("Click on Buy amount field")
    public void clickOnBuyAmount() {
        ScrollHelper.scrollToElement(buyField);
        buyField.click();
    }

    public String getBuyAmount() {
        return buyField.getAttribute("value");
    }

    @Step("Open Country dropdown")
    public CountryDropdown openCountryDropdown() {
        ScrollHelper.scrollToElement(selectCountryIconElement);
        selectCountryIconElement.click();
        return new CountryDropdown(selectCountryPopup);
    }

    public String getSellCurrency() {
        ScrollHelper.scrollToElement(sellCurrencyTextElement);
        WaitHelper.waitForElementHasText(sellCurrencyTextElement, PropertyHelper.getConf().pageLoadTimeout());
        return sellCurrencyTextElement.getText();
    }

    @Step("Select Sell currency '{currency}'")
    public void selectSellCurrency(String currency) {
        if (!getSellCurrency().equals(currency)) {
            sellCurrencyTextElement.click();
            CurrencyDropdown currencyDropdownComponent = new CurrencyDropdown(sellCurrencyDropdown);
            currencyDropdownComponent.selectCurrency(currency);
        }
    }

    @Step("Get {bank.bankName} for {country} currency")
    public BigDecimal getCurrencyRateForCountry(String country, CurrencyTableColumns bank) {
        CurrencyTable currencyTableComponent = new CurrencyTable(currencyTableElement);
        return new BigDecimal(currencyTableComponent.getBankRateForCountry(country, bank).replaceAll(",", ""));
    }

    @Step("Get alternative rate for {country} currency")
    public BigDecimal getAlternativeRateForCountry(String country) {
        CurrencyTable currencyTableComponent = new CurrencyTable(currencyTableElement);
        return new BigDecimal(currencyTableComponent.getAlternativeRateForCountry(country).replaceAll(",", ""));
    }

    @Step("Get other bank loss for {bank.bankName} with {country} currency")
    public BigDecimal getOtherBankLossForCountry(String country, CurrencyTableColumns bank) {
        CurrencyTable currencyTableComponent = new CurrencyTable(currencyTableElement);
        return new BigDecimal(currencyTableComponent.getOtherBankLossForCountry(country, bank).replaceAll(",", ""));
    }

    private void waitForPageLoading() {
        log.debug("Waiting page is loading");
        WaitHelper.waitForElementPresence(pageLoaderElement, PropertyHelper.getConf().elementTimeout());
        WaitHelper.waitForElementStayInvisible(pageLoaderElement, PropertyHelper.getConf().pageLoadTimeout());
    }
}
