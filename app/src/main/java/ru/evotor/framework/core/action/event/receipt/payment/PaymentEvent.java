package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentMapper;
import ru.evotor.framework.receipt.Payment;

public abstract class PaymentEvent implements IBundlable {
    private static final String KEY_PAYMENT_SELECTED = "payment";

    @NonNull
    private final Payment payment;

    PaymentEvent(@NonNull Payment payment) {
        this.payment = payment;
    }

    @Nullable
    static Payment getPayment(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentMapper.from(bundle.getBundle(KEY_PAYMENT_SELECTED));
    }

    @NonNull
    public Payment getPayment() {
        return payment;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_PAYMENT_SELECTED, PaymentMapper.toBundle(payment));
        return result;
    }

}
