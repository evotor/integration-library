package ru.evotor.devices.commons.exception;

import android.os.Looper;

public class DeviceServiceOperationOnMainThreadException extends RuntimeException {
    public DeviceServiceOperationOnMainThreadException() {
        super("It is forbidden to perform operations with device service in the main thread, it can be time-consuming");
    }

    public static void throwIfMainThread() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new DeviceServiceOperationOnMainThreadException();
        }
    }
}
