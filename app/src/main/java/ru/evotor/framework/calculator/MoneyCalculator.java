package ru.evotor.framework.calculator;

import java.math.BigDecimal;

public abstract class MoneyCalculator {
    public static final BigDecimal HUNDRED = new BigDecimal("100");
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
}
