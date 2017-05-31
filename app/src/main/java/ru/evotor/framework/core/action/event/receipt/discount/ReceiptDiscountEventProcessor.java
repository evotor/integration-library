package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;

import ru.evotor.framework.core.action.processor.ActionProcessor;

public abstract class ReceiptDiscountEventProcessor extends ActionProcessor {
    public ReceiptDiscountEventProcessor() {
        super(ReceiptDiscountEvent.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(ReceiptDiscountEvent.create(bundle), callback);
    }

    public abstract void call(ReceiptDiscountEvent event, Callback callback);
}

