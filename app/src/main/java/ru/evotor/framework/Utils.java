package ru.evotor.framework;

/**
 * Created by a.kuznetsov on 30/04/2017.
 */

public final class Utils {
    public static <T extends Enum<T>> T safeValueOf(Class<T> clazz, String name, T defaultValue) {
        try {
            return java.lang.Enum.valueOf(clazz, name);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }
}
