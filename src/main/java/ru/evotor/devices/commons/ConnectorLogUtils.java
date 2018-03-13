package ru.evotor.devices.commons;

import android.util.Log;

public class ConnectorLogUtils {

    private static boolean shouldLog = false;

    public static void setShouldLog(boolean shouldLog) {
        ConnectorLogUtils.shouldLog = shouldLog;
    }

    public static boolean shouldLog() {
        return shouldLog;
    }

    public static void log(String tag, String message) {
        if (shouldLog) {
            Log.w(tag, message);
        }
    }

    public static void log(String tag, String message, Throwable t) {
        if (shouldLog) {
            Log.w(tag, message, t);
        }
    }

    public static void logError(String tag, Throwable t) {
        Log.e(tag, t.getMessage(), t);
    }
}
