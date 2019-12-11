package ru.evotor.framework.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

/**
 * Базовый класс для реализации операций приложения.
 *
 * Методы класса упрощают обмен данными между операциями и смарт-терминалом.
 */
public abstract class IntegrationActivity extends Activity {

    private IntegrationResponse mIntegrationResponse = null;
    private Bundle mResultBundle = null;

    public final void setIntegrationResult(@Nullable Bundle result) {
        mResultBundle = result;
    }

    public final void setIntegrationResult(@Nullable IBundlable result) {
        mResultBundle = result == null ? null : result.toBundle();
    }

    public final void onError(int errorCode, @NonNull String errorMessage) {
        if (mIntegrationResponse != null) {
            mIntegrationResponse.onError(errorCode, errorMessage);
            mIntegrationResponse = null;
        }
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle icicle) {
        super.onCreate(icicle);

        Bundle bundle = getIntent().getBundleExtra(IntegrationManager.KEY_INTENT_DATA);
        if (bundle != null) {
            mIntegrationResponse = bundle
                    .getParcelable(IntegrationManager.KEY_INTEGRATION_RESPONSE);

            if (mIntegrationResponse != null) {
                mIntegrationResponse.onRequestContinued();
            }
        }
    }

    @Nullable
    protected Bundle getSourceBundle() {
        Bundle bundle = getIntent().getBundleExtra(IntegrationManager.KEY_INTENT_DATA);
        if (bundle == null) {
            return null;
        }
        return bundle.getParcelable(IntegrationManager.KEY_SOURCE_DATA);
    }

    @Override
    public void finish() {
        if (mIntegrationResponse != null) {
            // send the result bundle back if set, otherwise send an error.
            if (mResultBundle != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle(IntegrationManager.KEY_DATA, mResultBundle);
                mIntegrationResponse.onResult(bundle);
            }
            mIntegrationResponse = null;
        }
        super.finish();
    }

}
