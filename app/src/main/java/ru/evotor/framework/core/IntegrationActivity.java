package ru.evotor.framework.core;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by a.kuznetsov on 15/04/2017.
 */

public abstract class IntegrationActivity extends Activity {

    private IntegrationResponse mIntegrationResponse = null;
    private Bundle mResultBundle = null;

    /**
     * Set the result that is to be sent as the result of the request that caused this
     * Activity to be launched. If result is null or this method is never called then
     * the request will be canceled.
     *
     * @param result this is returned as the result of the AbstractAccountAuthenticator request
     */
    public final void setIntegrationResult(Bundle result) {
        mResultBundle = result;
    }

    /**
     * Retreives the AccountAuthenticatorResponse from either the intent of the icicle, if the
     * icicle is non-zero.
     *
     * @param icicle the save instance data of this Activity, may be null
     */
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        mIntegrationResponse =
                getIntent().getParcelableExtra(IntegrationManager.KEY_INTEGRATION_RESPONSE);

        if (mIntegrationResponse != null) {
            mIntegrationResponse.onRequestContinued();
        }
    }

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
