package com.gmail.kolesnyk;

import com.gmail.kolesnyk.data.CountryCurrency;
import com.gmail.kolesnyk.data.CurrencyTableColumns;
import com.gmail.kolesnyk.elements.CountryDropdown;
import com.gmail.kolesnyk.logging.DefaultListener;
import com.gmail.kolesnyk.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Listeners(DefaultListener.class)
public class UpdateCurrencyAccordingCountryTest extends BaseTest {

    @Test
    @Description("Rates and Currency updates after country changed")
    public void verifySwitchingCurrencyByCountry() {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        CountryDropdown countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(CountryCurrency.SPAIN);

        softVerify.assertEquals(calculatorPage.getSellCurrency(), CountryCurrency.SPAIN.getCurrency(),
                "Sell currency matches to expected");

        String country = CountryCurrency.USA.getCountryName();
        BigDecimal usaOfficialRateForFirstCountry = calculatorPage.getCurrencyRateForCountry(country, CurrencyTableColumns.OFFICIAL_RATE);
        BigDecimal usaAlternativeRateForFirstCountry = calculatorPage.getAlternativeRateForCountry(country);

        countryDropdownComponent = calculatorPage.openCountryDropdown();
        countryDropdownComponent.selectCountry(CountryCurrency.RUSSIA);

        softVerify.assertEquals(calculatorPage.getSellCurrency(), CountryCurrency.RUSSIA.getCurrency(),
                "Sell currency matches to expected");
        softVerify.assertTrue(calculatorPage.getCurrencyRateForCountry(country, CurrencyTableColumns.OFFICIAL_RATE).compareTo(usaOfficialRateForFirstCountry) != 0,
                "Official Rate for USA Dollar is changed");
        softVerify.assertTrue(calculatorPage.getAlternativeRateForCountry(country).compareTo(usaAlternativeRateForFirstCountry) != 0,
                "Alternative Rate for USA Dollar is changed");
    }
}
