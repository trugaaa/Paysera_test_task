package com.gmail.kolesnyk.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryCurrency {

    RUSSIA("Russia", "RUB"),
    SPAIN("Spain", "EUR"),
    USA("USA", "US Dollar");

    private final String countryName;
    private final String currency;
}
