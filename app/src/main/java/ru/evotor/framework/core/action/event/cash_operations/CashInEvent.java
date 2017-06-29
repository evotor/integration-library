package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

import ru.evotor.framework.core.action.datamapper.BundleUtils;

public class CashInEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_IN = "evotor.intent.action.cashOperation.CASH_IN";

    private static final String KEY_TOTAL = "total";

    @NonNull
    private final BigDecimal total;

    @NonNull
    public BigDecimal getTotal() {
        return total;
    }

    public CashInEvent(@NonNull String documentUuid, @NonNull BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }

    @Nullable
    public static CashInEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String documentUuid = CashOperationEvent.getDocumentUuid(bundle);
        if (documentUuid == null) {
            return null;
        }

        BigDecimal total = BundleUtils.getMoney(bundle, KEY_TOTAL);
        if (total == null) {
            return null;
        }

        return new CashInEvent(
                documentUuid,
                total
        );
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putString(KEY_TOTAL, total.toPlainString());
        return bundle;
    }
}