package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
        PaymentType paymentType = PaymentType.values()[bundle.getInt(KEY_PAYMENT_TYPE, 0)];
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
        bundle.putInt(KEY_PAYMENT_TYPE, payment.getPaymentType().ordinal());
        bundle.putString(KEY_USER_DESCRIPTION, payment.getUserDescription());

        return bundle;
    }

    private PaymentMapper() {
    }

}
