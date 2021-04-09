package com.gmail.kolesnyk;

import com.gmail.kolesnyk.data.AutoEraseFieldProvider;
import com.gmail.kolesnyk.logging.DefaultListener;
import com.gmail.kolesnyk.pages.CurrencyCalculatorPage;
import io.qameta.allure.Description;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(DefaultListener.class)
public class AutoEraseFieldTest extends BaseTest {

    @Test(dataProvider = "currencyValueDataProvider", dataProviderClass = AutoEraseFieldProvider.class)
    @Description("'Sell' amount box is being emptied when fills 'Buy' amount")
    public void verifySellAmountAutoErasing(String value) {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setSellAmount(value);
        calculatorPage.setBuyAmount(value);

        softVerify.assertEquals(calculatorPage.getSellAmount(), StringUtils.EMPTY, "Sell value is emptied");
    }

    @Test(dataProvider = "currencyValueDataProvider", dataProviderClass = AutoEraseFieldProvider.class)
    @Description("'Buy' amount box is being emptied when fills 'Sell' amount")
    public void verifyBuyAmountAutoErasing(String value) {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();
        calculatorPage.setBuyAmount(value);
        calculatorPage.setSellAmount(value);

        softVerify.assertEquals(calculatorPage.getBuyAmount(), StringUtils.EMPTY, "Buy value is emptied");
    }

    @Test(dataProvider = "singleFieldCurrencyDataProvider", dataProviderClass = AutoEraseFieldProvider.class)
    @Description("'Sell' amount box is not being emptied when clicks on 'Buy' amount")
    public void verifySellAmountIsNotErasedAfterClickToBuyAmount(String value) {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        calculatorPage.setSellAmount(value);
        calculatorPage.clickOnBuyAmount();

        softVerify.assertEquals(calculatorPage.getSellAmount(), value, "Sell value is not emptied");
    }

    @Test(dataProvider = "singleFieldCurrencyDataProvider", dataProviderClass = AutoEraseFieldProvider.class)
    @Description("'Buy' amount box is not being emptied when clicks on 'Sell' amount")
    public void verifyBuyAmountIsNotErasedAfterClickToSellAmount(String value) {
        CurrencyCalculatorPage calculatorPage = new CurrencyCalculatorPage();

        calculatorPage.setBuyAmount(value);
        calculatorPage.clickOnSellAmount();

        softVerify.assertEquals(calculatorPage.getBuyAmount(), value, "Buy value is not emptied");
    }
}
