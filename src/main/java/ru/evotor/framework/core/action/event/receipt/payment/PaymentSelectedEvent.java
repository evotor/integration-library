package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentPurpose;

public class PaymentSelectedEvent extends PaymentEvent {
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.SELECTED";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.payment.SELECTED";

    public PaymentSelectedEvent(@NonNull PaymentPurpose paymentPurpose) {
        super(paymentPurpose);
    }

    @Nullable
    public static PaymentSelectedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        PaymentPurpose paymentPurpose = PaymentEvent.getPaymentPurpose(bundle);
        if (paymentPurpose == null) {
            return null;
        }

        return new PaymentSelectedEvent(paymentPurpose);
    }
}
