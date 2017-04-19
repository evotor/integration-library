package ru.evotor.framework.core;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.ConcurrentHashMap;

import ru.evotor.framework.core.event.processor.EventProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */
public class IntegrationService extends Service {

    private final IIntegrationManager.Stub binder = new IIntegrationManager.Stub() {
        @Override
        public void call(IIntegrationManagerResponse response, String action, Bundle bundle) throws RemoteException {
            EventProcessor eventProcessor = processors.get(action);
            if (eventProcessor != null) {
                eventProcessor.process(response, bundle);
            }
        }
    };

    private ConcurrentHashMap<String, EventProcessor> processors = new ConcurrentHashMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        return binder.asBinder();
    }

    protected final void registerEventProcessor(EventProcessor eventProcessor) {
        if (eventProcessor == null) {
            throw new IllegalArgumentException("eventProcessor can't be null");
        }
        processors.put(eventProcessor.getEvent(), eventProcessor);
    }
}
