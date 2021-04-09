package com.gmail.kolesnyk.logging;

import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class SoftVerify {

    private SoftAssert softAssert;

    public SoftVerify() {
        this.softAssert = new SoftAssert();
    }

    @Step("Verification: {successMessage}")
    public <T> SoftVerify assertEquals(T actual, T expected, String successMessage) {
        softAssert.assertEquals(actual, expected);
        return this;
    }

    @Step("Verification: {successMessage}")
    public SoftVerify assertTrue(boolean condition, String successMessage) {
        softAssert.assertTrue(condition);
        return this;
    }

    public void assertAll() {
        softAssert.assertAll("Assertions failed");
    }
}