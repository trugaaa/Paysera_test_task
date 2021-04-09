package com.gmail.kolesnyk.elements;

import com.gmail.kolesnyk.data.CountryCurrency;
import com.gmail.kolesnyk.utils.ScrollHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class CountryDropdown extends BaseElement {

    @FindBy(css = "#countries-dropdown")
    private WebElement selectedCountryField;

    @FindBy(css = "[aria-labelledby='countries-dropdown']")
    private WebElement dropdownMenuCountryElement;

    private final By countryLocator = By.cssSelector("[aria-labelledby='countries-dropdown'] a");

    public CountryDropdown(WebElement element) {
        super(element);
    }

    @Step("Select country '{country.countryName}'")
    public void selectCountry(CountryCurrency country) {
        ScrollHelper.scrollToBottom();
        selectedCountryField.click();

        List<WebElement> countries = dropdownMenuCountryElement.findElements(countryLocator);
        Optional<WebElement> countryOptional = countries.stream()
                .filter(countryElement -> countryElement.getText().equals(country.getCountryName()))
                .findFirst();

        if (!countryOptional.isPresent()) {
            throw new RuntimeException("Country '" + country + "' is not found in list");
        }

        countryOptional.get().click();
    }
}
