package com.gmail.kolesnyk.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox");

    @Getter
    private final String name;
}
