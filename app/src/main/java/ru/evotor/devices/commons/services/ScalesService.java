package ru.evotor.devices.commons.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import ru.evotor.devices.commons.ConnectionWrapper;
import ru.evotor.devices.commons.DeviceServiceConnector;
import ru.evotor.devices.commons.IScalesService;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.result.ResultWeight;
import ru.evotor.devices.commons.scales.Weight;

import static ru.evotor.devices.commons.DeviceServiceConnector.ACTION_SCALES_SERVICE;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;

public class ScalesService extends AbstractService implements IScalesServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected Context context;

    protected volatile Boolean serviceConnected = null;
    protected IScalesService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = IScalesService.Stub.asInterface(binder);
            serviceConnected = true;
            for (ConnectionWrapper connectionWrapper : DeviceServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onScalesServiceConnected(ScalesService.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
            serviceConnected = false;
            startInitConnection(context, false);
            for (ConnectionWrapper connectionWrapper : DeviceServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onScalesServiceDisconnected();
            }
        }
    };

    public ScalesService() {
    }

    @Override
    public Boolean getServiceConnected() {
        return serviceConnected;
    }

    @Override
    public void startInitConnection(final Context appContext, final boolean force) {
        new Thread() {
            @Override
            public void run() {
                if (appContext == null) {
                    return;
                }
                context = appContext;
                if (service == null || force) {
                    Intent pr = new Intent(ACTION_SCALES_SERVICE);
                    pr.setPackage(TARGET_PACKAGE);
                    pr.setClassName(TARGET_PACKAGE, TARGET_CLASS_NAME);
                    serviceConnected = null;
                    boolean serviceBound = context.bindService(pr, serviceConnection, Service.BIND_AUTO_CREATE);
                    if (!serviceBound) {
                        serviceConnected = false;
                    }
                }
            }
        }.start();
    }

    @Override
    public void startDeinitConnection() {
        new Thread() {
            @Override
            public void run() {
                if (context == null) {
                    return;
                }
                context.unbindService(serviceConnection);
                service = null;
                for (ConnectionWrapper connectionWrapper : DeviceServiceConnector.getConnectionWrappers()) {
                    connectionWrapper.onScalesServiceDisconnected();
                }
            }
        }.start();
    }


    @Override
    public Weight getWeight(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultWeight result = service.getWeight(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

}
