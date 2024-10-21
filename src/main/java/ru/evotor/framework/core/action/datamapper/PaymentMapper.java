package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;

import java.math.BigDecimal;

import androidx.annotation.Nullable;
import ru.evotor.framework.BundleUtils;
import ru.evotor.framework.component.PaymentPerformer;
import ru.evotor.framework.payment.CashlessInfo;
import ru.evotor.framework.payment.PaymentSystem;
import ru.evotor.framework.receipt.Payment;


public final class PaymentMapper {

    private static final String KEY_UUID = "uuid";
    private static final String KEY_VALUE = "value";
    private static final String KEY_SYSTEM = "system";
    private static final String KEY_PERFORMER = "paymentPerformer";
    private static final String KEY_PURPOSE_IDENTIFIER = "purposeIdentifier";
    private static final String KEY_ACCOUNT_ID = "accountId";
    private static final String KEY_ACCOUNT_USER_DESCRIPTION = "accountUserDescription";
    private static final String KEY_IDENTIFIER = "identifier";
    private static final String KEY_CASHLESS_INFO = "cashlessInfo";

    @Nullable
    public static Payment from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String uuid = bundle.getString(KEY_UUID);
        BigDecimal value = BundleUtils.getMoney(bundle, KEY_VALUE);
        PaymentSystem paymentSystem = PaymentSystemMapper.from(bundle.getBundle(KEY_SYSTEM));
        PaymentPerformer paymentPerformer = PaymentPerformerMapper.INSTANCE.fromBundle(bundle.getBundle(KEY_PERFORMER));
        if (paymentPerformer == null) {
            paymentPerformer = new PaymentPerformer(
                    paymentSystem,
                    null,
                    null,
                    null,
                    null
            );
        }
        String purposeIdentifier = bundle.getString(KEY_PURPOSE_IDENTIFIER);
        String accountId = bundle.getString(KEY_ACCOUNT_ID);
        String accountUserDescription = bundle.getString(KEY_ACCOUNT_USER_DESCRIPTION);
        String identifier = bundle.getString(KEY_IDENTIFIER);
        CashlessInfo cashlessInfo = CashlessInfo.fromBundle(bundle.getBundle(KEY_CASHLESS_INFO));

        return new Payment(
                uuid,
                value,
                paymentPerformer.getPaymentSystem(),
                paymentPerformer,
                purposeIdentifier,
                accountId,
                accountUserDescription,
                identifier,
                cashlessInfo
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
        bundle.putBundle(KEY_SYSTEM, PaymentSystemMapper.toBundle(payment.getPaymentPerformer().getPaymentSystem()));
        bundle.putBundle(KEY_PERFORMER, PaymentPerformerMapper.INSTANCE.toBundle(payment.getPaymentPerformer()));
        bundle.putString(KEY_PURPOSE_IDENTIFIER, payment.getPurposeIdentifier());
        bundle.putString(KEY_ACCOUNT_ID, payment.getAccountId());
        bundle.putString(KEY_ACCOUNT_USER_DESCRIPTION, payment.getAccountUserDescription());
        bundle.putString(KEY_IDENTIFIER, payment.getIdentifier());
        if (payment.getCashlessInfo() != null) {
            bundle.putBundle(KEY_CASHLESS_INFO, payment.getCashlessInfo().toBundle());
        }

        return bundle;
    }

    private PaymentMapper() {
    }

}
