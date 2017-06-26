package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentSystem;

public class PaymentSelectedEvent extends PaymentEvent {
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.SELECTED";

    public PaymentSelectedEvent(@NonNull PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Nullable
    public static PaymentSelectedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        PaymentSystem paymentSystem = PaymentEvent.getPayment(bundle);
        if (paymentSystem == null) {
            return null;
        }

        return new PaymentSelectedEvent(
                paymentSystem
        );
    }
}
