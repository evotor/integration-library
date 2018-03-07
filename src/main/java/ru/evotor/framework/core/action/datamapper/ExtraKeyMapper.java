package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.evotor.framework.receipt.ExtraKey;

public final class ExtraKeyMapper {
    private static final String KEY_IDENTITY = "identity";
    private static final String KEY_APP_ID = "appId";
    private static final String KEY_DESCRIPTION = "description";

    @Nullable
    public static ExtraKey from(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String identity = bundle.getString(KEY_IDENTITY);
        String appId = bundle.getString(KEY_APP_ID);
        String description = bundle.getString(KEY_DESCRIPTION);
        return new ExtraKey(
                identity,
                appId,
                description
        );
    }

    @Nullable
    public static Bundle toBundle(@Nullable ExtraKey extraKey) {
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
