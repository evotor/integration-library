package ru.evotor.framework.core.action.event.receipt.ready_for_payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

public abstract class ReceiptReadyForPaymentEventProcessor extends ActionProcessor {
    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) {
        ReceiptReadyForPaymentEvent event = ReceiptReadyForPaymentEvent.create(bundle);
        if (event == null) {
            return;
        }
        call(action, event, callback);
    }

    public abstract void call(@NonNull String action,
                              @NonNull ReceiptReadyForPaymentEvent event,
                              @NonNull Callback callback);
}

