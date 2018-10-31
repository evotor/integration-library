package ru.evotor.framework.core.action.event.receipt.ready_for_payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class ReceiptReadyForPaymentEventResult implements IBundlable {

    private static final String KEY_RECEIPT_PAYMENT_ENABLED = "receiptPaymentEnabled";

    @Nullable
    public static ReceiptReadyForPaymentEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        boolean sendUtmDocEnabled = bundle.getBoolean(KEY_RECEIPT_PAYMENT_ENABLED);

        return new ReceiptReadyForPaymentEventResult(sendUtmDocEnabled);
    }

    private final boolean receiptPaymentEnabled;

    public ReceiptReadyForPaymentEventResult(boolean receiptPaymentEnabled) {
        this.receiptPaymentEnabled = receiptPaymentEnabled;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_RECEIPT_PAYMENT_ENABLED, receiptPaymentEnabled);
        return bundle;
    }

    public boolean isReceiptPaymentEnabled() {
        return receiptPaymentEnabled;
    }
}