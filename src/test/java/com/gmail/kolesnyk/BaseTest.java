package com.gmail.kolesnyk;

import com.gmail.kolesnyk.driver.DriverProvider;
import com.gmail.kolesnyk.logging.DefaultListener;
import com.gmail.kolesnyk.logging.SoftVerify;
import com.gmail.kolesnyk.utils.PropertyHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Slf4j
@Listeners(DefaultListener.class)
public class BaseTest {

    private final String startUrl = PropertyHelper.getConf().startUrl();
    protected SoftVerify softVerify;

    @BeforeClass
    public void beforeClass() {
        WebDriver driver = DriverProvider.getDriver();
        log.info("Open url: " + startUrl);
        driver.get(startUrl);
    }

    @BeforeMethod
    public void beforeTest() {
        softVerify = new SoftVerify();
    }

    @AfterMethod
    public void assertAll() {
        softVerify.assertAll();
    }

    @AfterClass
    public void afterClass() {
        log.info("Tear down driver");
        DriverProvider.tearDown();
    }
}
