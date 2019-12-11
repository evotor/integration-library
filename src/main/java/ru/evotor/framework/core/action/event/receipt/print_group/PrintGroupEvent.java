package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper;
import ru.evotor.framework.payment.PaymentSystem;

/**
 * Родительский класс события разделения чека на несколько печатныъ групп {@link PrintGroupRequiredEvent}.
 */
public abstract class PrintGroupEvent implements IBundlable {
    private static final String KEY_SELECTED_PAYMENT_SYSTEM = "paymentSystem";

    /**
     * @deprecated Has no longer meaning as combined payment can occur
     */
    @Nullable
    @Deprecated
    private final PaymentSystem paymentSystem;

    PrintGroupEvent(@Nullable PaymentSystem paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    /**
     * @deprecated Has no longer meaning as combined payment can occur
     */
    @Nullable
    @Deprecated
    static PaymentSystem getPaymentSystem(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentSystemMapper.from(bundle.getBundle(KEY_SELECTED_PAYMENT_SYSTEM));
    }

    /**
     * @deprecated Has no longer meaning as combined payment can occur
     */
    @Nullable
    @Deprecated
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