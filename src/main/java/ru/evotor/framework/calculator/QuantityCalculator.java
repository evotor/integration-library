package ru.evotor.framework.calculator;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class QuantityCalculator {

    private static final int QUANTITY_PRECISION = 3;

    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static BigDecimal subtract(BigDecimal value, BigDecimal subtrahend) {
        return value.subtract(subtrahend);
    }

    public static BigDecimal divide(BigDecimal quantity, BigDecimal divisor) {
        return quantity.divide(divisor, QUANTITY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal quantity, BigDecimal multiplicand) {
        return quantity.multiply(multiplicand).setScale(QUANTITY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    @NonNull
    public static BigDecimal toBigDecimal(double value) {
        return round(new BigDecimal(value));
    }

    @NonNull
    public static BigDecimal round(@NonNull BigDecimal value) {
        Objects.requireNonNull(value);
        return value.setScale(QUANTITY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }
}
