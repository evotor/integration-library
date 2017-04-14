package ru.evotor.devices.commons;

import android.content.Context;
import android.os.DeadObjectException;

import java.util.concurrent.CopyOnWriteArrayList;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.DeviceServiceRuntimeException;
import ru.evotor.devices.commons.exception.ServiceNotConnectedException;
import ru.evotor.devices.commons.services.IPrinterServiceWrapper;
import ru.evotor.devices.commons.services.PrinterService;

public class DeviceServiceConnector {

    protected static final String TAG = "DeviceServiceConnector";

    public static final String TARGET_PACKAGE = "ru.evotor.devices";
    public static final String TARGET_CLASS_NAME = "ru.evotor.devices.DeviceService";

    public static final String ACTION_PRINTER_SERVICE = "ru.evotor.devices.PrintService";

    protected static Context context;

    protected static final PrinterService printerService = new PrinterService();

    protected final static CopyOnWriteArrayList<ConnectionWrapper> connectionWrappers = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<ConnectionWrapper> getConnectionWrappers() {
        return connectionWrappers;
    }

    public static void addConnectionWrapper(ConnectionWrapper connectionWrapper) {
        connectionWrappers.add(connectionWrapper);
    }

    public static void removeConnectionWrapper(ConnectionWrapper connectionWrapper) {
        connectionWrappers.remove(connectionWrapper);
    }

    public static void clearConnectionWrappers() {
        connectionWrappers.clear();
    }


    public static IPrinterServiceWrapper getPrinterService() throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        printerService.waitInitService(context);
        return printerService;
    }

    public static void startInitConnections(final Context appContext) {
        startInitConnections(appContext, false);
    }

    public static void startInitConnections(final Context appContext, final boolean force) {
        if (appContext == null) {
            return;
        }

        DeviceServiceConnector.context = appContext;

        new Thread(new Runnable() {
            @Override
            public void run() {
                printerService.initConnection(context, force);
            }
        }).start();

    }


    public static void startDeinitConnections() {
        if (context == null) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                printerService.deInitConnection();
            }
        }).start();
    }

    public static void processException(Exception exc) throws DeviceServiceException {
        if (exc instanceof DeadObjectException) {
            DeviceServiceConnector.startInitConnections(context, true);
            throw new ServiceNotConnectedException(exc);
        } else if (exc instanceof RuntimeException) {
            throw new DeviceServiceRuntimeException((RuntimeException) exc);
        }
        exc.printStackTrace();
    }
}