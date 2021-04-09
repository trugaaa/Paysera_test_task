package com.gmail.kolesnyk;

import com.gmail.kolesnyk.data.CountryCurrency;
import com.gmail.kolesnyk.data.CurrencyExchangeProvider;
import com.gmail.kolesnyk.data.CurrencyTableColumns;
import com.gmail.kolesnyk.logging.DefaultListener;
import com.gmail.kolesnyk.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;

@Listeners(DefaultListener.class)
public class CurrencyExchangeLossTest extends BaseTest {

    @Test(dataProvider = "randomInputValueDataProvider", dataProviderClass = CurrencyExchangeProvider.class)
    @Description("Loss amount is displayed when alternative bank provides better rate")
    public void verifyCurrencyLoss(String value) {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.selectSellCurrency(CountryCurrency.SPAIN.getCurrency());

        calculatorPage.interSellAmount(value);

        String country = CountryCurrency.USA.getCountryName();
        final BigDecimal swedBankAmount = calculatorPage.getCurrencyRateForCountry(country, CurrencyTableColumns.SWEDBANK_AMOUNT);
        final BigDecimal alternativeAmount = calculatorPage.getCurrencyRateForCountry(country, CurrencyTableColumns.COMPANY_AMOUNT);
        final BigDecimal swedBankAmountLoss = calculatorPage.getOtherBankLossForCountry(country, CurrencyTableColumns.SWEDBANK_AMOUNT);

        softVerify.assertEquals(swedBankAmount.subtract(alternativeAmount), swedBankAmountLoss,
                "Amount loss for Swed Bank is correct");
    }
}
