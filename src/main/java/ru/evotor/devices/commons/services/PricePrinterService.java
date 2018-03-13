package ru.evotor.devices.commons.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import ru.evotor.devices.commons.DeviceServiceConnector;
import ru.evotor.devices.commons.IPricePrinterService;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.result.ResultVoid;

import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;
import static ru.evotor.devices.commons.InternalConstants.ACTION_PRICE_PRINTER_SERVICE;

public class PricePrinterService extends AbstractService implements IPricePrinterServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected Context context;

    protected volatile Boolean serviceConnected = null;
    protected IPricePrinterService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = IPricePrinterService.Stub.asInterface(binder);
            serviceConnected = true;
            //TODO (M) call wrappers here
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
            serviceConnected = false;
            startInitConnection(context, false);
            //TODO (M) call wrappers here
        }
    };

    public PricePrinterService() {
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
                    Intent pr = new Intent(ACTION_PRICE_PRINTER_SERVICE);
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
                //TODO (M) call wrappers here
            }
        }.start();
    }

    @Override
    public void beforePrintPrices(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultVoid result = service.beforePrintPrices(deviceId);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }


    @Override
    public void afterPrintPrices(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultVoid result = service.afterPrintPrices(deviceId);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }


    @Override
    public void printPrice(int deviceId, String name, String price, String barcode, String code) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultVoid result = service.printPrice(deviceId, name, price, barcode, code);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

}
