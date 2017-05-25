package ru.evotor.framework.calculator;

import java.math.BigDecimal;

public abstract class PercentCalculator {
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final int PERCENT_PRECISION = 6;

    public static BigDecimal calcPercent(BigDecimal total, BigDecimal part) {
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return part.multiply(HUNDRED).divide(total, PERCENT_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

}
