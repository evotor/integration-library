package ru.evotor.framework.core.action.event.cash_drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CashDrawerOpenEvent extends CashDrawerEvent {
    public static final String BROADCAST_ACTION_CASH_DRAWER_OPEN = "evotor.intent.action.cashDrawer.OPEN";

    public CashDrawerOpenEvent(int cashDrawerId) {
        super(cashDrawerId);
    }

    private CashDrawerOpenEvent(@NonNull Bundle extras) {
        super(extras);
    }

    @Nullable
    public static CashDrawerOpenEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new CashDrawerOpenEvent(bundle);
    }
}