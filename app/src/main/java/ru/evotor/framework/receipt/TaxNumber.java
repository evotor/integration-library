package ru.evotor.framework.receipt;

import java.math.BigDecimal;

/**
 * Created by a.kuznetsov on 02/05/2017.
 */

public enum TaxNumber {
    VAT_18(BigDecimal.valueOf(18)),
    VAT_10(BigDecimal.valueOf(10)),
    NO_VAT(BigDecimal.ZERO),
    VAT_18_118(BigDecimal.valueOf(18)),
    VAT_10_110(BigDecimal.valueOf(10)),
    VAT_0(BigDecimal.ZERO);

    private final BigDecimal value;

    TaxNumber(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
