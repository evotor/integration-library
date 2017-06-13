package ru.evotor.framework.core.action.event.cash_drawer;

import android.os.Bundle;

public abstract class CashDrawerEvent {
    private static final String KEY_CASH_DRAWER_ID = "cashDrawerId";

    private final int cashDrawerId;

    public CashDrawerEvent(Bundle extras) {
        this(
                extras.getInt(KEY_CASH_DRAWER_ID, -1)
        );
    }

    public CashDrawerEvent(int cashDrawerId) {
        this.cashDrawerId = cashDrawerId;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putInt(KEY_CASH_DRAWER_ID, cashDrawerId);
        return result;
    }

    public int getKeyCashDrawerId() {
        return cashDrawerId;
    }
}
