package ru.evotor.framework.core.event.processor;

import android.os.Bundle;

import ru.evotor.framework.core.event.EvoV2ReceiptBeforeProductAddedEvent;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class EvoV2ReceiptBeforeProductAddedEventProcessor extends EventProcessor {
    public EvoV2ReceiptBeforeProductAddedEventProcessor() {
        super("evo.v2.receipt.beforeProductAdded");
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(EvoV2ReceiptBeforeProductAddedEvent.create(bundle), callback);
    }

    public abstract void call(EvoV2ReceiptBeforeProductAddedEvent event, Callback callback);
}

