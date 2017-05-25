package ru.evotor.framework.core.action.processor;


import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

import ru.evotor.framework.core.IIntegrationManagerResponse;
import ru.evotor.framework.core.IntegrationManager;
import ru.evotor.framework.core.IntegrationResponse;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public abstract class ActionProcessor {

    private String action;

    public ActionProcessor(String action) {
        if (action == null) {
            throw new IllegalArgumentException("action can't be null");
        }

        this.action = action;
    }

    public void process(IIntegrationManagerResponse response, Bundle bundle) {
        process(bundle, new Callback(response, bundle));
    }

    public abstract void process(Bundle bundle, Callback callback);

    public String getAction() {
        return action;
    }

    public final class Callback {
        private IIntegrationManagerResponse response;
        private Bundle sourceData;

        private Callback(IIntegrationManagerResponse response, Bundle sourceData) {
            this.response = response;
            this.sourceData = sourceData;
        }

        public final void startActivity(Intent intent) throws RemoteException {
            if (!intent.hasExtra(IntegrationManager.KEY_INTENT_DATA)) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(IntegrationManager.KEY_INTEGRATION_RESPONSE, new IntegrationResponse(response));
                bundle.putParcelable(IntegrationManager.KEY_SOURCE_DATA, sourceData);
                intent.putExtra(IntegrationManager.KEY_INTENT_DATA, bundle);
            }

            final Bundle data = new Bundle();
            data.putParcelable(IntegrationManager.KEY_INTENT, intent);

            response.onResult(data);
        }

        public final void onResult(Bundle bundle) throws RemoteException {
            Bundle result = new Bundle();
            result.putBundle(IntegrationManager.KEY_DATA, bundle);
            response.onResult(result);
        }

        public final void onError(int errorCode, String errorMessage) throws RemoteException {
            response.onError(errorCode, errorMessage);
        }

        public final void skip() throws RemoteException {
            Bundle result = new Bundle();
            result.putBoolean(IntegrationManager.KEY_SKIP, true);

            response.onResult(result);
        }
    }
}
