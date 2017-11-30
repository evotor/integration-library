package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.payment.PaymentPurpose;

public final class PaymentPurposeMapper {
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_PAYMENT_SYSTEM_ID = "paymentSystemId";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_ACCOUNT_ID = "account";
    private static final String KEY_USER_MESSAGE = "userMessage";

    @Nullable
    public static PaymentPurpose from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String identifier = bundle.getString(KEY_IDENTIFIER);
        String paymentSystemId = bundle.getString(KEY_PAYMENT_SYSTEM_ID);
        BigDecimal total = BundleUtils.getMoney(bundle, KEY_TOTAL);
        String account = bundle.getString(KEY_ACCOUNT_ID);
        String userMessage = bundle.getString(KEY_USER_MESSAGE);
        return new PaymentPurpose(
                identifier,
                paymentSystemId,
                total,
                account,
                userMessage
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable PaymentPurpose paymentPurpose) {
        if (paymentPurpose == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IDENTIFIER, paymentPurpose.getIdentifier());
        bundle.putString(KEY_PAYMENT_SYSTEM_ID, paymentPurpose.getPaymentSystemId());
        bundle.putString(KEY_TOTAL, paymentPurpose.getTotal().toPlainString());
        bundle.putString(KEY_ACCOUNT_ID, paymentPurpose.getAccountId());
        bundle.putString(KEY_USER_MESSAGE, paymentPurpose.getUserMessage());

        return bundle;
    }

    private PaymentPurposeMapper() {
    }

}
