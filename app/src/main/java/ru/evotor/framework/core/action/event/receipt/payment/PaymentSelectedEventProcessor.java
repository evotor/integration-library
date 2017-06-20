package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

public abstract class PaymentSelectedEventProcessor extends ActionProcessor {
    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) {
        PaymentSelectedEventResult event = PaymentSelectedEventResult.create(bundle);

        if (event == null) {
            return;
        }

        call(
                action,
                event,
                callback
        );
    }

    public abstract void call(@NonNull String action, @NonNull PaymentSelectedEventResult event, @NonNull Callback callback);
}

