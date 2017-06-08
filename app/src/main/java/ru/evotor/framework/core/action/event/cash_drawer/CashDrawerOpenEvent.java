package ru.evotor.framework.core.action.event.cash_drawer;

import android.os.Bundle;

public class CashDrawerOpenEvent extends CashDrawerEvent {
    public static final String BROADCAST_ACTION_CASH_DRAWER_OPEN = "evotor.intent.action.cashDrawer.OPEN";

    public CashDrawerOpenEvent(Bundle extras) {
        super(extras);
    }

    public CashDrawerOpenEvent(int cashDrawerId) {
        super(cashDrawerId);
    }
}