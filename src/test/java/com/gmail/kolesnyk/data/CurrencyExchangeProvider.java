package com.gmail.kolesnyk.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class CurrencyExchangeProvider {
    @DataProvider(name = "randomInputValueDataProvider")
    public static Object[][] getRandomInputValue() {
        return new Object[][]{{RandomStringUtils.randomNumeric(3)}};
    }
}
