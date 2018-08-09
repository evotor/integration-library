package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentPurposeMapper;
import ru.evotor.framework.payment.PaymentPurpose;

public abstract class PaymentEvent implements IBundlable {
    private static final String KEY_SELECTED_PAYMENT_PURPOSE = "paymentPurpose";

    @NonNull
    private final PaymentPurpose paymentPurpose;

    PaymentEvent(@NonNull PaymentPurpose paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    @Nullable
    static PaymentPurpose getPaymentPurpose(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentPurposeMapper.from(bundle.getBundle(KEY_SELECTED_PAYMENT_PURPOSE));
    }

    @NonNull
    public PaymentPurpose getPaymentPurpose() {
        return paymentPurpose;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_SELECTED_PAYMENT_PURPOSE, PaymentPurposeMapper.toBundle(paymentPurpose));
        return result;
    }

}
