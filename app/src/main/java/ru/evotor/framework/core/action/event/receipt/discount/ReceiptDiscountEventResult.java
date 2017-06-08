package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Objects;

import ru.evotor.framework.Utils;
import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;

public class ReceiptDiscountEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_RECEIPT_EXTRA = "extra";

    @Nullable
    public static ReceiptDiscountEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String resultName = bundle.getString(KEY_RESULT);
        BigDecimal discount = MoneyCalculator.round(new BigDecimal(bundle.getString(KEY_DISCOUNT, "0")));
        return new ReceiptDiscountEventResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                discount,
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        );
    }

    @NonNull
    private final Result result;
    @NonNull
    private final BigDecimal discount;
    @Nullable
    private final SetExtra extra;

    public ReceiptDiscountEventResult(
            @NonNull Result result,
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra
    ) {
        Objects.requireNonNull(result);
        Objects.requireNonNull(discount);

        this.result = result;
        this.discount = discount;
        this.extra = extra;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putString(KEY_DISCOUNT, discount.toPlainString());
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        return bundle;
    }

    @NonNull
    public BigDecimal getDiscount() {
        return discount;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
