package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.payment.PaymentSystem;
import ru.evotor.framework.receipt.Payment;


public final class PaymentMapper {

    private static final String KEY_UUID = "uuid";
    private static final String KEY_VALUE = "value";
    private static final String KEY_SYSTEM = "system";
    private static final String KEY_PURPOSE_IDENTIFIER = "purposeIdentifier";
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_ACCOUNT_USER_DESCRIPTION = "accountUserDescription";
    private static final String KEY_IDENTIFIER = "identifier";

    @Nullable
    public static Payment from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String uuid = bundle.getString(KEY_UUID);
        BigDecimal value = BundleUtils.getMoney(bundle, KEY_VALUE);
        PaymentSystem paymentSystem = PaymentSystemMapper.from(bundle.getBundle(KEY_SYSTEM));
        String purposeIdentifier = bundle.getString(KEY_PURPOSE_IDENTIFIER);
        String accountId = bundle.getString(KEY_ACCOUNT_ID);
        String accountUserDescription = bundle.getString(KEY_ACCOUNT_USER_DESCRIPTION);
        String identifier = bundle.getString(KEY_IDENTIFIER);

        return new Payment(
                uuid,
                value,
                paymentSystem,
                purposeIdentifier,
                accountId,
                accountUserDescription,
                identifier
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable Payment payment) {
        if (payment == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UUID, payment.getUuid());
        bundle.putString(KEY_VALUE, payment.getValue().toPlainString());
        bundle.putBundle(KEY_SYSTEM, PaymentSystemMapper.toBundle(payment.getSystem()));
        bundle.putString(KEY_PURPOSE_IDENTIFIER, payment.getPurposeIdentifier());
        bundle.putString(KEY_ACCOUNT_ID, payment.getAccountId());
        bundle.putString(KEY_ACCOUNT_USER_DESCRIPTION, payment.getAccountUserDescription());
        bundle.putString(KEY_IDENTIFIER, payment.getIdentifier());

        return bundle;
    }

    private PaymentMapper() {
    }

}
