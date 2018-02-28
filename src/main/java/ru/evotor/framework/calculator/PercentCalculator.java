package ru.evotor.framework.calculator;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

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

    public static BigDecimal toBigDecimal(double value) {
        BigDecimal bigDecimalValue = new BigDecimal(value);
        return bigDecimalValue.setScale(PERCENT_PRECISION, BigDecimal.ROUND_HALF_UP);
    }
  
    @NonNull
    public static BigDecimal round(@NonNull BigDecimal value) {
        Objects.requireNonNull(value);
        return value.setScale(PERCENT_PRECISION, BigDecimal.ROUND_HALF_UP);
    }
}
