package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class BeforePositionsEditedEventProcessor extends ActionProcessor {

    @Override
    public void process(String action, Bundle bundle, Callback callback) {
        call(action, new BeforePositionsEditedEvent(bundle), callback);
    }

    public abstract void call(String action, BeforePositionsEditedEvent event, Callback callback);
}

