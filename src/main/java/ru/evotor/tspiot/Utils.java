package ru.evotor.tspiot;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.io.Serializable;

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

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> Class<T> readClass(Parcel parcel) {
        Serializable serializable = parcel.readSerializable();

        if (serializable == null) {
            return null;
        }

        try {
            return (Class<T>) serializable;
        } catch (Exception exception) {
            return null;
        }
    }

    @Nullable
    public static <T extends Parcelable> T readData(Class<? extends T> classType, Parcel parcel) {
        try {
            if (classType == null) {
                return parcel.readParcelable(null);
            }

            return parcel.readParcelable(classType.getClassLoader());
        } catch (Exception exception) {
            return null;
        }
    }

    public static String toString(@Nullable Object object) {
        return object == null ? "null" : object.toString();
    }

    public static String toString(@Nullable int[] ints) {
        if (ints == null) {
            return "null";
        } else if (ints.length == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            for (int i = 0; i < ints.length; i++) {
                sb.append(ints[i]);

                if (i != ints.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");

            return sb.toString();
        }
    }

    public static String toString(@Nullable Object[] objects) {
        if (objects == null) {
            return "null";
        } else if (objects.length == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder();

            sb.append("[");
            for (int i = 0; i < objects.length; i++) {
                sb.append(objects[i]);

                if (i != objects.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");

            return sb.toString();
        }
    }
}
