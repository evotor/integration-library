package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.Utils;

import static ru.evotor.framework.calculator.MoneyCalculator.HUNDRED;
import static ru.evotor.framework.calculator.MoneyCalculator.MONEY_PRECISION;

public class ReceiptDiscountEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_DISCOUNT = "discount";

    public static ReceiptDiscountEventResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        BigDecimal discount = new BigDecimal(bundle.getLong(KEY_DISCOUNT, 0)).setScale(MONEY_PRECISION).divide(HUNDRED, BigDecimal.ROUND_FLOOR);
        return new ReceiptDiscountEventResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                discount
        );
    }

    private final Result result;
    private final BigDecimal discount;

    public ReceiptDiscountEventResult(Result result, BigDecimal discount) {
        this.result = result;
        this.discount = discount;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putLong(KEY_DISCOUNT, discount.multiply(HUNDRED).longValue());
        return bundle;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Result getResult() {
        return result;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
