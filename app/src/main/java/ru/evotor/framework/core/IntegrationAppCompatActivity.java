package ru.evotor.framework.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by a.kuznetsov on 15/04/2017.
 */

public abstract class IntegrationAppCompatActivity extends AppCompatActivity {

    private IntegrationResponse mIntegrationResponse = null;
    private Bundle mResultBundle = null;

    public final void setIntegrationResult(Bundle result) {
        mResultBundle = result;
    }

    public final void onError(int errorCode, String errorMessage) {
        if (mIntegrationResponse != null) {
            mIntegrationResponse.onError(errorCode, errorMessage);
            mIntegrationResponse = null;
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle icicle) {
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
