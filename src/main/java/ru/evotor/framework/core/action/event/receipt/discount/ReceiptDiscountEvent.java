package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.BundleUtils;

public class ReceiptDiscountEvent implements IBundlable {
    private static final String TAG = "ReceiptDiscountEvent";

    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.receiptDiscount";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.receiptDiscount";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_DISCOUNT = "discount";

    @Nullable
    public static ReceiptDiscountEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);
        BigDecimal discount = BundleUtils.getMoney(bundle, KEY_DISCOUNT);
        if (discount == null) {
            return null;
        }
        return new ReceiptDiscountEvent(receiptUuid, discount);
    }

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final BigDecimal discount;

    public ReceiptDiscountEvent(
            @NonNull String receiptUuid,
            @NonNull BigDecimal discount
    ) {
        this.receiptUuid = receiptUuid;
        this.discount = discount;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putString(KEY_DISCOUNT, discount.toPlainString());
        return result;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    @NonNull
    public BigDecimal getDiscount() {
        return discount;
    }
}
