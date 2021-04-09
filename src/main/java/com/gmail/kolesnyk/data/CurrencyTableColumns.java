package com.gmail.kolesnyk.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyTableColumns {
    CURRENCY("Currency", "Currency"),
    OFFICIAL_RATE("Official rate", "Official rate"),
    COMPANY_AMOUNT("Company amount", "sera rate"),
    SWEDBANK_AMOUNT("Swedbank amount", "Swedbank amount"),
    SEB_AMOUNT("SEB amount", "SEB amount"),
    CITADELE_AMOUNT("Citadele amount", "Citadele amount"),
    LUMINOR_AMOUNT("Luminor amount", "Luminor amount");

    private final String bankName;
    private final String locator;
}
