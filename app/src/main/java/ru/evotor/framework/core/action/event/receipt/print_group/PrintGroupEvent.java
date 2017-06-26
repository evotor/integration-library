package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper;
import ru.evotor.framework.payment.PaymentSystem;

public abstract class PrintGroupEvent implements IBundlable {
    private static final String KEY_PAYMENT_SELECTED = "paymentSystem";

    @NonNull
    private final PaymentSystem paymentSystem;

    PrintGroupEvent(@NonNull PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    @Nullable
    static PaymentSystem getPayment(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SELECTED));
    }

    @NonNull
    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_PAYMENT_SELECTED, PaymentSystemMapper.toBundle(paymentSystem));
        return result;
    }

}
