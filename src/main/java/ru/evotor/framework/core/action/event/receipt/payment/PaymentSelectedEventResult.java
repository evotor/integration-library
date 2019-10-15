package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.PaymentPurposeMapper;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;
import ru.evotor.framework.payment.PaymentPurpose;

/**
 * Результат обработки события {@link PaymentSelectedEvent}.
 */
public class PaymentSelectedEventResult implements IBundlable {

    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_PAYMENT_PURPOSES = "paymentPurposes";

    @Nullable
    public static PaymentSelectedEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        Parcelable[] purposesParcelables = bundle.getParcelableArray(KEY_PAYMENT_PURPOSES);
        if (purposesParcelables == null) {
            return null;
        }
        List<PaymentPurpose> paymentPurposes = new ArrayList<>();
        for (int i = 0; i < purposesParcelables.length; i++) {
            if (purposesParcelables[i] instanceof Bundle) {
                PaymentPurpose paymentPurpose = PaymentPurposeMapper.from((Bundle) purposesParcelables[i]);
                if (paymentPurpose != null) {
                    paymentPurposes.add(paymentPurpose);
                }
            }
        }

        return new PaymentSelectedEventResult(
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                paymentPurposes
        );
    }

    @Nullable
    private final SetExtra extra;
    @NonNull
    private final List<PaymentPurpose> paymentPurposes;

    public PaymentSelectedEventResult(
            @Nullable SetExtra extra,
            @NonNull List<PaymentPurpose> paymentPurposes
    ) {
        this.extra = extra;
        this.paymentPurposes = paymentPurposes;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        Parcelable[] accountsParcelables = new Parcelable[this.paymentPurposes.size()];
        for (int i = 0; i < accountsParcelables.length; i++) {
            accountsParcelables[i] = PaymentPurposeMapper.toBundle(paymentPurposes.get(i));
        }
        bundle.putParcelableArray(KEY_PAYMENT_PURPOSES, accountsParcelables);
        return bundle;
    }

    /**
     * Возвращает дополнителье поля чека, которые были указаны при создании результата.
     * @return список объектов {@link SetExtra}.
     */
    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    /**
     * Возвращает список платежей, указанных при создании результата.
     * @return список объектов {@link PaymentPurpose}.
     */
    @NonNull
    public List<PaymentPurpose> getPaymentPurposes() {
        return paymentPurposes;
    }
}