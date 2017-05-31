package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import java.math.BigDecimal;

public class ReceiptDiscountEvent {
    private static final String TAG = "ReceiptDiscountEvent";

    public static final String NAME = "evo.v2.receipt.sell.receiptDiscount";
    private static final String KEY_DISCOUNT = "discount";

    public static ReceiptDiscountEvent create(Bundle bundle) {
        BigDecimal discount = new BigDecimal(bundle.getString(KEY_DISCOUNT, "0"));
        return new ReceiptDiscountEvent(discount);
    }

    private BigDecimal discount;

    public ReceiptDiscountEvent(BigDecimal discount) {
        this.discount = discount;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_DISCOUNT, discount.toPlainString());
        return result;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
