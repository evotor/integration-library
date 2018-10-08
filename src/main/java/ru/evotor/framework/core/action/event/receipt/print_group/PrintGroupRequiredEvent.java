package ru.evotor.framework.core.action.event.receipt.print_group;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.payment.PaymentSystem;

public class PrintGroupRequiredEvent extends PrintGroupEvent {
    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.printGroup.REQUIRED";
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.printGroup.REQUIRED";

    public PrintGroupRequiredEvent(@Nullable PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Nullable
    public static PrintGroupRequiredEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        PaymentSystem paymentSystem = PrintGroupEvent.getPaymentSystem(bundle);
        if (paymentSystem == null) {
            return null;
        }

        return new PrintGroupRequiredEvent(paymentSystem);
    }
}