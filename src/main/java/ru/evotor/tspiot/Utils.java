package ru.evotor.tspiot;

import android.os.Parcel;
import androidx.annotation.Nullable;

public final class Utils {

    @Nullable
    public static Integer readInteger(Parcel parcel) {
        try {
            return (Integer) parcel.readValue(Integer.class.getClassLoader());
        } catch (Exception ex) {
            return null;
        }
    }

    @Nullable
    public static Boolean readBoolean(Parcel parcel) {
        try {
            return (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        } catch (Exception ex) {
            return null;
        }
    }
}
