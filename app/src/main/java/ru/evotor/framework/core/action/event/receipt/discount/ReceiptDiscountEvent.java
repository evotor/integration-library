package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.calculator.MoneyCalculator;

public class ReceiptDiscountEvent {
    private static final String TAG = "ReceiptDiscountEvent";

    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.receiptDiscount";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.receiptDiscount";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_DISCOUNT = "discount";

    public static ReceiptDiscountEvent create(Bundle bundle) {
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);
        BigDecimal discount = MoneyCalculator.round(new BigDecimal(bundle.getString(KEY_DISCOUNT, "0")));
        return new ReceiptDiscountEvent(receiptUuid, discount);
    }

    private final String receiptUuid;
    private final BigDecimal discount;

    public ReceiptDiscountEvent(
            String receiptUuid,
            BigDecimal discount
    ) {
        this.receiptUuid = receiptUuid;
        this.discount = discount;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putString(KEY_DISCOUNT, discount.toPlainString());
        return result;
    }

    public String getReceiptUuid() {
        return receiptUuid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
