package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.core.action.event.cash_operations.CashOperationEvent;

public class CashInEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_IN = "evotor.intent.action.cashOperation.IN";

    private static final String KEY_TOTAL = "total";

    private final BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public CashInEvent(Bundle extras) {
        super(extras);
        this.total = new BigDecimal(extras.getString(KEY_TOTAL, "0"));
    }

    public CashInEvent(String documentUuid, BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }
}