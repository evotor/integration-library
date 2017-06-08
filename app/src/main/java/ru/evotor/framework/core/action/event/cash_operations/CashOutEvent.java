package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;

import java.math.BigDecimal;

public class CashOutEvent extends CashOperationEvent {
    public static final String BROADCAST_ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT";

    private static final String KEY_TOTAL = "total";

    private final BigDecimal total;

    public BigDecimal getTotal() {
        return total;
    }

    public CashOutEvent(Bundle extras) {
        super(extras);
        this.total = new BigDecimal(extras.getString(KEY_TOTAL, "0"));
    }

    public CashOutEvent(String documentUuid, BigDecimal total) {
        super(documentUuid);
        this.total = total;
    }
}