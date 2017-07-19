package ru.evotor.framework;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public final class Utils {
    public static <T extends Enum<T>> T safeValueOf(Class<T> clazz, String name, T defaultValue) {
        Objects.requireNonNull(clazz);
        if (name == null) {
            return defaultValue;
        }

        try {
            return java.lang.Enum.valueOf(clazz, name);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    @NonNull
    public static <T, R> List<R> filterByClass(@NonNull List<T> source, @NonNull Class<R> clazz) {
        List<R> list = new ArrayList<>();
        for (T t : source) {
            if (clazz.isInstance(t)) {
                list.add(((R) t));
            }
        }
        return list;
    }

    @Nullable
    public static <T> List<T> convertParcelables(@Nullable Parcelable[] parcelables, @NonNull Class<T> clazz) {
        if (parcelables != null) {
            List<T> exports = new ArrayList<>();
            for (Parcelable parcelable : parcelables) {
                if (clazz.isInstance(parcelable)) {
                    exports.add((T) parcelable);
                }
            }
            return exports;
        } else {
            return null;
        }
    }
}
