package ru.evotor.framework.calculator;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class MoneyCalculator {
  
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final int MONEY_PRECISION = 2;

    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static BigDecimal subtract(BigDecimal value, BigDecimal subtrahend) {
        return value.subtract(subtrahend);
    }

    public static BigDecimal divide(BigDecimal money, BigDecimal divisor) {
        return money.divide(divisor, MONEY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal money, BigDecimal multiplicand) {
        return money.multiply(multiplicand).setScale(MONEY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal calcPart(BigDecimal total, BigDecimal percent) {
        return total.multiply(percent).divide(HUNDRED, MONEY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal toBigDecimal(double value) {
        BigDecimal bigDecimalValue = new BigDecimal(value);
        return bigDecimalValue.setScale(MONEY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    @NonNull
    public static BigDecimal round(@NonNull BigDecimal value) {
        Objects.requireNonNull(value);
        return value.setScale(MONEY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }
}
