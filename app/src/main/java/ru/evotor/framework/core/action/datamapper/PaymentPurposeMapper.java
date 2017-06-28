package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.payment.PaymentPurpose;

public final class PaymentPurposeMapper {
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_TOTAL = "total";
    private static final String KEY_ACCOUNT_ID = "account";

    @Nullable
    public static PaymentPurpose from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String identifier = bundle.getString(KEY_IDENTIFIER);
        BigDecimal total = new BigDecimal(bundle.getString(KEY_TOTAL));
        String account = bundle.getString(KEY_ACCOUNT_ID);
        return new PaymentPurpose(
                identifier,
                total,
                account
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable PaymentPurpose paymentPurpose) {
        if (paymentPurpose == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IDENTIFIER, paymentPurpose.getIdentifier());
        bundle.putString(KEY_TOTAL, paymentPurpose.getTotal().toPlainString());
        bundle.putString(KEY_ACCOUNT_ID, paymentPurpose.getAccountId());

        return bundle;
    }

    private PaymentPurposeMapper() {
    }

}
