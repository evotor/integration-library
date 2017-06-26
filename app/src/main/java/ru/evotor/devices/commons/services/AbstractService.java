package ru.evotor.devices.commons.services;


import android.content.Context;

import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.ServiceNotConnectedException;

public abstract class AbstractService {

    protected Context context;

    protected abstract void startInitConnection(Context appContext, boolean force);

    protected abstract void startDeinitConnection();

    protected abstract Boolean getServiceConnected();

    public void waitInitService(Context context) throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        if (context == null) {
            throw new ServiceNotConnectedException("Unable to connect to DeviceService");
        }

        this.context = context;

        Boolean connected = this.getServiceConnected();

        if (connected == null || !connected) {
            this.startInitConnection(context, false);
        }

        while ((connected = this.getServiceConnected()) == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException exc) {
            }
        }

        if (!connected) {
            throw new ServiceNotConnectedException("Unable to connect to DeviceService");
        }
    }
}
