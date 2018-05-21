package ru.evotor.framework.receipt

import java.math.BigDecimal

enum class TaxNumber(val value: BigDecimal) {
    VAT_18(BigDecimal.valueOf(18)),
    VAT_10(BigDecimal.valueOf(10)),
    NO_VAT(BigDecimal.ZERO),
    VAT_18_118(BigDecimal.valueOf(18)),
    VAT_10_110(BigDecimal.valueOf(10)),
    VAT_0(BigDecimal.ZERO)
}
