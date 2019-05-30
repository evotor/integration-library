package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Процессор (класс для обработки) события изменения чека: {@link BeforePositionsEditedEvent}.
 */
public abstract class BeforePositionsEditedEventProcessor extends ActionProcessor {

    @Override
    public void process(@NonNull String action, @Nullable Bundle bundle, @NonNull Callback callback) throws RemoteException {
        BeforePositionsEditedEvent event = BeforePositionsEditedEvent.create(bundle);
        if (event == null) {
            callback.skip();
            return;
        }
        call(action, event, callback);
    }

    public abstract void call(@NonNull String action, @NonNull BeforePositionsEditedEvent event, @NonNull Callback callback);
}

