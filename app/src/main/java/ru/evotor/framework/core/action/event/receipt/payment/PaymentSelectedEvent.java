package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.receipt.Payment;

public class PaymentSelectedEvent extends PaymentEvent {
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.SELECTED";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.payment.SELECTED";

    public PaymentSelectedEvent(@NonNull Payment payment) {
        super(payment);
    }

    @Nullable
    public static PaymentSelectedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        Payment payment = PaymentEvent.getPayment(bundle);
        if (payment == null) {
            return null;
        }

        return new PaymentSelectedEvent(
                payment
        );
    }
}
