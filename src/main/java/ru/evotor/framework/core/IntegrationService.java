package ru.evotor.framework.core;

import android.app.Service;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Базовый класс для реализации служб приложения.
 * <p>
 * Методы класса упрощают обмен данными между службами и смарт-терминалом.
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
                    if ((!(e instanceof BadParcelableException))
                            && (!(e instanceof IllegalArgumentException))
                            && (!(e instanceof IllegalStateException))
                            && (!(e instanceof NullPointerException))
                            && (!(e instanceof SecurityException))
                            && (!(e instanceof UnsupportedOperationException))
                            && (!(e instanceof NetworkOnMainThreadException))
                    ) {
                        throw new IllegalStateException(e);
                    }
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

    private final ConcurrentHashMap<String, ActionProcessor> processors = new ConcurrentHashMap<>();

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

    /**
     * Создаёт обработчик.
     *
     * @return коллекция пар "Событие":"Обработчик события".
     */
    @Nullable
    protected abstract Map<String, ActionProcessor> createProcessors();
}
