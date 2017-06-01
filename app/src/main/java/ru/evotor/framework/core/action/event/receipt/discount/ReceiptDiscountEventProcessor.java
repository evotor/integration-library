package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import ru.evotor.framework.core.action.processor.ActionProcessor;

public abstract class ReceiptDiscountEventProcessor extends ActionProcessor {
    @Override
    public void process(String action, Bundle bundle, Callback callback) {
        call(action, ReceiptDiscountEvent.create(bundle), callback);
    }

    public abstract void call(String action, ReceiptDiscountEvent event, Callback callback);
}

