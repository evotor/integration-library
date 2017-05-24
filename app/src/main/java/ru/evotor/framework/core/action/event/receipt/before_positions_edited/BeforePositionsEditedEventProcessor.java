package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class BeforePositionsEditedEventProcessor extends ActionProcessor {
    public BeforePositionsEditedEventProcessor() {
        super(BeforePositionsEditedEvent.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(BeforePositionsEditedEvent.create(bundle), callback);
    }

    public abstract void call(BeforePositionsEditedEvent event, Callback callback);
}

