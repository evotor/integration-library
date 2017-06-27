package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper;
import ru.evotor.framework.payment.PaymentSystem;

public abstract class PaymentEvent implements IBundlable {
    private static final String KEY_SELECTED_PAYMENT_SYSTEM = "paymentSystem";

    @NonNull
    private final PaymentSystem paymentSystem;

    PaymentEvent(@NonNull PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    @Nullable
    static PaymentSystem getPayment(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentSystemMapper.from(bundle.getBundle(KEY_SELECTED_PAYMENT_SYSTEM));
    }

    @NonNull
    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_SELECTED_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentSystem));
        return result;
    }

}
