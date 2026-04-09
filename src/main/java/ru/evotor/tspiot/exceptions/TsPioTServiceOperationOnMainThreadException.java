package ru.evotor.tspiot.exceptions;

import android.os.Looper;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;

public class TsPioTServiceOperationOnMainThreadException extends TsPioTServiceException {
    public TsPioTServiceOperationOnMainThreadException() {
        super("It is forbidden to perform operations with TS PIoT service in the main thread, it can be time-consuming");
    }

    public static void throwIfMainThread() throws TsPioTServiceOperationOnMainThreadException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new TsPioTServiceOperationOnMainThreadException();
        }
    }
}
