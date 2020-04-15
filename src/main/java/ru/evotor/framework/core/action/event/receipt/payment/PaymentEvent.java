package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentPurposeMapper;
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper;
import ru.evotor.framework.payment.PaymentPurpose;

/**
 * Родительский класс события выбора оплаты чека {@link PaymentSelectedEvent}.
 */
public abstract class PaymentEvent implements IBundlable {
    private static final String KEY_SELECTED_PAYMENT_PURPOSE = "paymentPurpose";
    private static final String KEY_SELECTED_PAYMENT_SYSTEM = "paymentSystem";

    @NonNull
    private final PaymentPurpose paymentPurpose;

    PaymentEvent(@NonNull PaymentPurpose paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    @Nullable
    static PaymentPurpose getPaymentPurpose(@Nullable Bundle bundle) {
        return bundle == null ? null : PaymentPurposeMapper.from(bundle.getBundle(KEY_SELECTED_PAYMENT_PURPOSE));
    }

    /**
     * Возвращает платёж, которым покупатель оплачивает чек.
     * @return {@link PaymentPurpose}
     */
    @NonNull
    public PaymentPurpose getPaymentPurpose() {
        return paymentPurpose;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putBundle(KEY_SELECTED_PAYMENT_PURPOSE, PaymentPurposeMapper.toBundle(paymentPurpose));
        result.putBundle(KEY_SELECTED_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(
                paymentPurpose.getPaymentPerformer() != null
                        ? paymentPurpose.getPaymentPerformer().getPaymentSystem()
                        : null));
        return result;
    }

}
