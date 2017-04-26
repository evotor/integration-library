package ru.evotor.framework.core.action.event.processor;

import android.os.Bundle;

import ru.evotor.framework.core.action.event.EvoV2ReceiptBeforeProductAddedEvent;
import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class EvoV2ReceiptBeforeProductAddedEventProcessor extends ActionProcessor {
    public EvoV2ReceiptBeforeProductAddedEventProcessor() {
        super(EvoV2ReceiptBeforeProductAddedEvent.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(EvoV2ReceiptBeforeProductAddedEvent.create(bundle), callback);
    }

    public abstract void call(EvoV2ReceiptBeforeProductAddedEvent event, Callback callback);
}

