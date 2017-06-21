package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.Utils;
import ru.evotor.framework.receipt.Payment;
import ru.evotor.framework.receipt.PaymentType;

public final class PaymentMapper {
    private static final String KEY_PAYMENT_TYPE = "paymentType";
    private static final String KEY_USER_DESCRIPTION = "userDescription";

    @Nullable
    public static Payment from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        PaymentType paymentType = Utils.safeValueOf(PaymentType.class, bundle.getString(KEY_USER_DESCRIPTION), PaymentType.UNKNOWN);
        String userDescription = bundle.getString(KEY_USER_DESCRIPTION);
        return new Payment(
                paymentType,
                userDescription
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Payment payment) {
        if (payment == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PAYMENT_TYPE, payment.getPaymentType().name());
        bundle.putString(KEY_USER_DESCRIPTION, payment.getUserDescription());

        return bundle;
    }

    private PaymentMapper() {
    }

}
