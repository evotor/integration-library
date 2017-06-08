package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

public class CashInEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_IN = "evotor.intent.action.cashOperation.IN";

    private static final String KEY_TOTAL = "total";

    @Nullable
    private final BigDecimal total;

    @Nullable
    public BigDecimal getTotal() {
        return total;
    }

    private CashInEvent(@NonNull Bundle extras) {
        super(extras);
        this.total = new BigDecimal(extras.getString(KEY_TOTAL, "0"));
    }

    public CashInEvent(@Nullable String documentUuid, @Nullable BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }

    @Nullable
    public static CashInEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new CashInEvent(bundle);
    }
}