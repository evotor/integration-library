package ru.evotor;

import android.os.Looper;

public class OperationOnMainThreadException extends RuntimeException {
    public OperationOnMainThreadException() {
        super("It is forbidden to perform this operation in the main thread, it can be time-consuming");
    }

    public static void throwIfMainThread() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new OperationOnMainThreadException();
        }
    }
}
