package ru.evotor.framework.core.event.processor;


import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

import ru.evotor.framework.core.IIntegrationManagerResponse;
import ru.evotor.framework.core.IntegrationManager;
import ru.evotor.framework.core.IntegrationResponse;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class EventProcessor {

    private String event;

    public EventProcessor(String event) {
        if (event == null) {
            throw new IllegalArgumentException("event can't be null");
        }

        this.event = event;
    }

    public void process(IIntegrationManagerResponse response, Bundle bundle) {
        process(bundle, new Callback(response));
    }

    public abstract void process(Bundle bundle, Callback callback);

    public String getEvent() {
        return event;
    }

    final class Callback {
        private IIntegrationManagerResponse response;

        private Callback(IIntegrationManagerResponse response) {
            this.response = response;
        }

        public final void startActivity(Intent intent) throws RemoteException {
            intent.putExtra(IntegrationManager.KEY_INTEGRATION_RESPONSE, new IntegrationResponse(response));

            final Bundle data = new Bundle();
            data.putParcelable(IntegrationManager.KEY_INTENT, intent);

            response.onResult(data);
        }

        public final void onResult(Bundle bundle) throws RemoteException {
            Bundle result = new Bundle();
            result.putBundle(IntegrationManager.KEY_DATA, bundle);
            response.onResult(result);
        }
    }
}
