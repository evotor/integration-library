package ru.evotor.devices.commons;

import android.content.Context;
import android.os.DeadObjectException;

import java.util.concurrent.CopyOnWriteArrayList;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.DeviceServiceRuntimeException;
import ru.evotor.devices.commons.exception.ServiceNotConnectedException;
import ru.evotor.devices.commons.services.IPrinterServiceWrapper;
import ru.evotor.devices.commons.services.IScalesServiceWrapper;
import ru.evotor.devices.commons.services.PrinterService;
import ru.evotor.devices.commons.services.ScalesService;

/**
 * Класс, необходимый для инициализации встроенного или подключённого оборудования.
 */
public class DeviceServiceConnector {

    protected static final String TAG = "DeviceServiceConnector";

    public static final String TARGET_PACKAGE = "ru.evotor.devices";
    public static final String TARGET_CLASS_NAME = "ru.evotor.devices.DeviceService";

    public static final String ACTION_PRINTER_SERVICE = "ru.evotor.devices.PrintService";
    public static final String ACTION_SCALES_SERVICE = "ru.evotor.devices.ScalesService";

    protected static Context context;

    protected static final PrinterService printerService = new PrinterService();
    protected static final ScalesService scalesService = new ScalesService();

    protected final static CopyOnWriteArrayList<ConnectionWrapper> connectionWrappers = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<ConnectionWrapper> getConnectionWrappers() {
        return connectionWrappers;
    }

    /**
     * Получает событие о подключении оборудования.
     * @param connectionWrapper интерфейс для выполнения дейтвий при подключении и отключении принтера и весов.
     */
    public static void addConnectionWrapper(ConnectionWrapper connectionWrapper) {
        connectionWrappers.add(connectionWrapper);
    }

    public static void removeConnectionWrapper(ConnectionWrapper connectionWrapper) {
        connectionWrappers.remove(connectionWrapper);
    }

    public static void clearConnectionWrappers() {
        connectionWrappers.clear();
    }


    /**
     * Получает экземпляр службы принтера. Не может принимать значение <code>null</code>.
     *
     * @return экземпляр службы принтера.
     * @throws ServiceNotConnectedException
     */
    public static IPrinterServiceWrapper getPrinterService() throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        printerService.waitInitService(context);
        return printerService;
    }

    public static IScalesServiceWrapper getScalesService() throws ServiceNotConnectedException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        scalesService.waitInitService(context);
        return scalesService;
    }

    /**
     * Инициализирует встроенное и подключённое оборудование
     * @param appContext контекст приложения.
     */
    public static void startInitConnections(final Context appContext) {
        startInitConnections(appContext, false);
    }

    public static void startInitConnections(final Context appContext, final boolean force) {
        if (appContext == null) {
            return;
        }

        DeviceServiceConnector.context = appContext;

        printerService.startInitConnection(context, force);
        scalesService.startInitConnection(context, force);
    }


    public static void startDeinitConnections() {
        if (context == null) {
            return;
        }
        printerService.startDeinitConnection();
        scalesService.startDeinitConnection();
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