package com.gmail.kolesnyk.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class AutoEraseFieldProvider {
    @DataProvider(name = "currencyValueDataProvider")
    public static Object[][] manageDataProvider() {
        return new Object[][]{
                {"1"},
                {"0"},
                {RandomStringUtils.randomNumeric(3)},
                {"-" + RandomStringUtils.randomNumeric(3)},
                {" "},
                {"0.0001"},
                {RandomStringUtils.randomAlphabetic(1)},
                {RandomStringUtils.randomAscii(10)},
                {RandomStringUtils.randomAlphanumeric(500)},
        };
    }

    @DataProvider(name = "singleFieldCurrencyDataProvider")
    public static Object[][] singleFieldDataProvider() {
        return new Object[][]{
                {RandomStringUtils.randomNumeric(3)},
                {"-" + RandomStringUtils.randomNumeric(3)},
                {"-0.00"}
        };
    }
}
