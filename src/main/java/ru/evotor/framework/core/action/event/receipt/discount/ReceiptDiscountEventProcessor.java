package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

public abstract class ReceiptDiscountEventProcessor extends ActionProcessor {
    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) throws RemoteException {
        ReceiptDiscountEvent event = ReceiptDiscountEvent.create(bundle);

        if (event == null) {
            callback.skip();
            return;
        }

        call(
                action,
                event,
                callback
        );
    }

    public abstract void call(@NonNull String action, @NonNull ReceiptDiscountEvent event, @NonNull Callback callback);
}

