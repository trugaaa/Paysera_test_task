package com.gmail.kolesnyk.elements;

import com.gmail.kolesnyk.data.CountryCurrency;
import com.gmail.kolesnyk.data.CurrencyTableColumns;
import com.gmail.kolesnyk.utils.PropertyHelper;
import com.gmail.kolesnyk.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public class CurrencyTable extends BaseElement {

    private final By currencyRowNameLocator = By.xpath("//td[contains(@data-ng-if,\"PROVIDERS.OFFICIAL\")]");

    public CurrencyTable(WebElement element) {
        super(element);
    }

    public String getBankRateForCountry(String country, CurrencyTableColumns bank) {
        WebElement currencyRow = getCurrencyRow(country);
        CurrencyTableRow rowTableComponent = new CurrencyTableRow(currencyRow);
        return rowTableComponent.getBankRate(bank);
    }

    public String getOtherBankLossForCountry(String country, CurrencyTableColumns bank) {
        WebElement currencyRow = getCurrencyRow(country);
        CurrencyTableRow rowTableComponent = new CurrencyTableRow(currencyRow);
        return rowTableComponent.getOtherBankLoss(bank);
    }

    public String getAlternativeRateForCountry(String country) {
        WebElement usaCurrencyRow = getCurrencyRow(country);
        CurrencyTableRow rowTableComponent = new CurrencyTableRow(usaCurrencyRow);
        return rowTableComponent.getAlternativeRate();
    }

    private WebElement getCurrencyRow(String country) {
        WaitHelper.waitForElementPresence(getComponentElement(), currencyRowNameLocator,
                PropertyHelper.getConf().pageLoadTimeout());

        String currency = CountryCurrency.valueOf(country).getCurrency();

        List<WebElement> rows = getComponentElement().findElements(currencyRowNameLocator);
        Optional<WebElement> usaRow = rows.stream()
                .filter(row -> row.getText().contains(currency))
                .findFirst();

        if (!usaRow.isPresent()) {
            throw new RuntimeException("Row with currency " + currency + " not found");
        }

        return usaRow.get();
    }
}
