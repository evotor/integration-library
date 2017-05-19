package ru.evotor.framework.core.action.event.receipt.before_positions_removed;

import android.os.Bundle;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class BeforePositionsRemovedEventProcessor extends ActionProcessor {
    public BeforePositionsRemovedEventProcessor() {
        super(BeforePositionsRemovedEvent.NAME);
    }

    @Override
    public void process(Bundle bundle, Callback callback) {
        call(BeforePositionsRemovedEvent.create(bundle), callback);
    }

    public abstract void call(BeforePositionsRemovedEvent event, Callback callback);
}

