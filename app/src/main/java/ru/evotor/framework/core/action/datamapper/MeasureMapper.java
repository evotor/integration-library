package ru.evotor.framework.core.action.datamapper;

import android.os.Bundle;

import ru.evotor.framework.inventory.Measure;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public final class MeasureMapper {
    private static final String KEY_UUID = "uuid";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRECISION = "precision";

    public static Measure from(Bundle bundle) {
        String uuid = bundle.getString(KEY_UUID);
        String name = bundle.getString(KEY_NAME);
        int precision = bundle.getInt(KEY_PRECISION);
        return new Measure(
                uuid,
                name,
                precision
        );
    }

    public static Bundle toBundle(Measure measure) {
        if (measure == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_UUID, measure.getUuid());
        bundle.putString(KEY_NAME, measure.getName());
        bundle.putInt(KEY_PRECISION, measure.getPrecision());

        return bundle;
    }

    private MeasureMapper() {
    }

}
