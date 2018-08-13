package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class PrintGroupRequiredEvent extends PrintGroupEvent {
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.printGroup.REQUIRED";
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.printGroup.REQUIRED";

    public PrintGroupRequiredEvent() {
        super();
    }

    @Nullable
    public static PrintGroupRequiredEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new PrintGroupRequiredEvent();
    }
}
