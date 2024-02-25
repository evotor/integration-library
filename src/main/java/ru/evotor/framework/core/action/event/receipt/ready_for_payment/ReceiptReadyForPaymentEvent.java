package ru.evotor.framework.core.action.event.receipt.ready_for_payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class ReceiptReadyForPaymentEvent implements IBundlable {

    public static final String NAME_PERMISSION = "evo.v2.receipt.payment.DISABLE";

    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.receiptReadyForPayment";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.receiptReadyForPayment";
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.receiptReadyForPayment";
    public static final String NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.receiptReadyForPayment";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    @Nullable
    public static ReceiptReadyForPaymentEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);
        return new ReceiptReadyForPaymentEvent(receiptUuid);
    }

    @NonNull
    private final String receiptUuid;

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    public ReceiptReadyForPaymentEvent(@NonNull String receiptUuid) {
        this.receiptUuid = receiptUuid;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        return result;
    }

}