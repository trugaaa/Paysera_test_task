package com.gmail.kolesnyk.driver;

import com.gmail.kolesnyk.utils.AppConfig;
import com.gmail.kolesnyk.utils.PropertyHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverProvider {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        AppConfig config = PropertyHelper.getConf();
        if (driver == null) {
            if (config.webDriverBrowserName().equalsIgnoreCase(Browsers.CHROME.getName())) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (config.webDriverBrowserName().equalsIgnoreCase(Browsers.FIREFOX.getName())) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(config.webDriverBrowserName() + " is not supported");
            }

            driver.manage().timeouts().pageLoadTimeout(config.pageLoadTimeout(), TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(config.elementTimeout(), TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(config.elementTimeout(), TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
