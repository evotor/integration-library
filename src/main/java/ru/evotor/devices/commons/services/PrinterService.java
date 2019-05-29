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
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.printer.PrinterDocument;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultVoid;

import static ru.evotor.devices.commons.DeviceServiceConnector.ACTION_PRINTER_SERVICE;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;

public class PrinterService extends AbstractService implements IPrinterServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected volatile Boolean serviceConnected = null;
    protected ru.evotor.devices.commons.IPrinterService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ru.evotor.devices.commons.IPrinterService.Stub.asInterface(binder);
            serviceConnected = true;
            for (ConnectionWrapper connectionWrapper : DeviceServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onPrinterServiceConnected(PrinterService.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
            serviceConnected = false;
            startInitConnection(context, false);
            for (ConnectionWrapper connectionWrapper : DeviceServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onPrinterServiceDisconnected();
            }
        }
    };

    public PrinterService() {
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
                    Intent pr = new Intent(ACTION_PRINTER_SERVICE);
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
                    connectionWrapper.onPrinterServiceDisconnected();
                }
            }
        }.start();
    }

    /**
     * Возвращает количество символов, которые помещаются в одной строке чека.
     *
     * @param deviceId указывает устройство, для которого вызывается метод.
     * @return количество символов в одной строке чека.
     * @throws DeviceServiceException
     */
    public int getAllowableSymbolsLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getAllowableSymbolsLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    /**
     * Возвращает доступную для печати ширину бумаги в пикселях.
     *
     * @param deviceId указывает устройство, для которого вызывается метод.
     * @return ширину чековой ленты в пикселях
     * @throws DeviceServiceException
     */
    public int getAllowablePixelLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getAllowablePixelLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    /**
     * Печатает указанный массив объектов (текст, изображения, штрихкоды).
     *
     * @param deviceId указывает устройство, для которого вызывается метод. ВАЖНО! Печать возможна только на встроенном принтере, поэтому вместо номера устройства всегда передавайте константу <code>ru.evotor.devices.commons.Constants.DEFAULT_DEVICE_INDEX</code>.
     * @param printerDocument массив объектов для печати.
     * @throws DeviceServiceException
     */
    public void printDocument(int deviceId, PrinterDocument printerDocument) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultVoid result = service.printDocument(deviceId, printerDocument);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

}
