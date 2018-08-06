package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.BundleUtils;
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper;
import ru.evotor.framework.payment.PaymentSystem;

public abstract class PaymentEvent implements IBundlable {
    private static final String KEY_SELECTED_PAYMENT_SYSTEM = "paymentSystem";
    private static final String KEY_SELECTED_PAYMENT_MAX_SUM = "maxSum";

    @NonNull
    private final PaymentSystem paymentSystem;
    private final BigDecimal maxSum;

    PaymentEvent(@NonNull PaymentSystem paymentSystem, @NonNull BigDecimal maxSum) {
        this.paymentSystem = paymentSystem;
        this.maxSum = maxSum;
    }

    @Nullable
    static PaymentSystem getPayment(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentSystemMapper.from(bundle.getBundle(KEY_SELECTED_PAYMENT_SYSTEM));
    }

    @Nullable
    static BigDecimal getMaxSum(@Nullable Bundle bundle) {
        return bundle == null ? null : BundleUtils.getMoney(bundle, KEY_SELECTED_PAYMENT_MAX_SUM);
    }

    @NonNull
    public PaymentSystem getPaymentSystem() {
        return paymentSystem;
    }

    @NonNull
    public BigDecimal getMaxSum() {
        return maxSum;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_SELECTED_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentSystem));
        result.putString(KEY_SELECTED_PAYMENT_MAX_SUM, maxSum.toPlainString());
        return result;
    }

}
