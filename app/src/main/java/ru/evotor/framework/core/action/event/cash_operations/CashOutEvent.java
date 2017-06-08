package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;

public class CashOutEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT";

    private static final String KEY_TOTAL = "total";

    @Nullable
    private final BigDecimal total;

    @Nullable
    public BigDecimal getTotal() {
        return total;
    }

    public CashOutEvent(@Nullable String documentUuid, @Nullable BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }

    private CashOutEvent(@NonNull Bundle extras) {
        super(extras);
        this.total = new BigDecimal(extras.getString(KEY_TOTAL, "0"));
    }

    @Nullable
    public static CashOutEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new CashOutEvent(bundle);
    }
}