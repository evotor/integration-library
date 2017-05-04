package ru.evotor.framework.core.action.event.receipt.before_position_added;

import android.os.Bundle;

import ru.evotor.framework.core.action.event.receipt.before_position_added.BeforePositionAddedEvent;
import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class BeforePositionAddedEventProcessor extends ActionProcessor {
    public BeforePositionAddedEventProcessor() {
        super(BeforePositionAddedEvent.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(BeforePositionAddedEvent.create(bundle), callback);
    }

    public abstract void call(BeforePositionAddedEvent event, Callback callback);
}

