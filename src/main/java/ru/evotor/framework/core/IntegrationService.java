package ru.evotor.framework.core;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Родительский класс интеграционных служб необходимых для обработки и реакции на события смарт-терминала.
 */
public abstract class IntegrationService extends Service {

    private final IIntegrationManager.Stub binder = new IIntegrationManager.Stub() {
        @Override
        public void call(IIntegrationManagerResponse response, String action, Bundle bundle) throws RemoteException {
            ActionProcessor processor = processors.get(action);
            if (processor != null) {
                try {
                    processor.process(action, response, bundle);
                } catch (Exception e) {
                    Log.e("IntegrationService", "Message: " + e.getMessage(), e);
                    throw e;
                }

            } else {
                Log.e("IntegrationService", "Processor for action \"" + action + "\" not found in service " + IntegrationService.this.getClass().getName(), new IllegalStateException());

                // skip
                Bundle result = new Bundle();
                result.putBoolean(IntegrationManager.KEY_SKIP, true);

                response.onResult(result);
            }
        }
    };

    private ConcurrentHashMap<String, ActionProcessor> processors = new ConcurrentHashMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        return binder.asBinder();
    }

    protected final void registerProcessor(String action, ActionProcessor processor) {
        Objects.requireNonNull(action);
        Objects.requireNonNull(processor);

        processors.put(action, processor);
    }

    @Override
    @CallSuper
    public void onCreate() {
        super.onCreate();

        Map<String, ActionProcessor> processorMap = createProcessors();
        if (processorMap != null) {
            for (Map.Entry<String, ActionProcessor> entry : processorMap.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    registerProcessor(entry.getKey(), entry.getValue());
                }
            }
        }

    }

    @Nullable
    protected abstract Map<String, ActionProcessor> createProcessors();
}
