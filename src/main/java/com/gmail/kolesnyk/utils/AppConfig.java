package com.gmail.kolesnyk.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:./application.properties"})
public interface AppConfig extends Config {
    @Key("start.url")
    String startUrl();

    @Key(value = "webdriver.browser.name")
    String webDriverBrowserName();

    @Key("timeouts.page")
    int pageLoadTimeout();

    @Key("timeouts.element")
    int elementTimeout();
}
