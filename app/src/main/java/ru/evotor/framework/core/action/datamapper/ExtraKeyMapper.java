package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;

import ru.evotor.framework.receipt.ExtraKey;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class ExtraKeyMapper {
    private static final String KEY_IDENTITY = "identity";
    private static final String KEY_APP_ID = "appId";
    private static final String KEY_DESCRIPTION = "description";

    public static ExtraKey from(Bundle bundle) {
        String identity = bundle.getString(KEY_IDENTITY);
        String appId = bundle.getString(KEY_APP_ID);
        String description = bundle.getString(KEY_DESCRIPTION);
        return new ExtraKey(
                identity,
                appId,
                description
        );
    }

    public static Bundle toBundle(ExtraKey extraKey) {
        if (extraKey == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_IDENTITY, extraKey.getIdentity());
        bundle.putString(KEY_APP_ID, extraKey.getAppId());
        bundle.putString(KEY_DESCRIPTION, extraKey.getDescription());

        return bundle;
    }

    private ExtraKeyMapper() {
    }

}
