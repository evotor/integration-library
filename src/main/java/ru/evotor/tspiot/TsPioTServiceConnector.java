package ru.evotor.tspiot;

import android.content.Context;
import android.os.DeadObjectException;
import java.util.concurrent.CopyOnWriteArrayList;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.tspiot.exceptions.ServiceNotConnectedException;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.exceptions.TsPioTServiceRuntimeException;
import ru.evotor.tspiot.service.ITsPioTConnectionWrapper;
import ru.evotor.tspiot.service.ITsPioTServiceWrapper;
import ru.evotor.tspiot.service.TsPioTService;

public class TsPioTServiceConnector {

    protected static final String TAG = "TsPioTServiceConnector";

    public static final String ACTION_TSPIOT_SERVICE = "evotor.intent.action.TSPIOT_SERVICE";
    public static final String TARGET_PACKAGE = "ru.esp.umesm";
    public static final String TARGET_CLASS_NAME = "ru.esp.worker.worker.IntegrationMarksCheckService";

    protected final static TsPioTService tsPioTService = new TsPioTService();

    protected final static CopyOnWriteArrayList<ITsPioTConnectionWrapper> connectionWrappers = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<ITsPioTConnectionWrapper> getConnectionWrappers() {
        return connectionWrappers;
    }

    public static void addConnectionWrapper(ITsPioTConnectionWrapper connectionWrapper) {
        connectionWrappers.add(connectionWrapper);
    }

    public static void removeConnectionWrapper(ITsPioTConnectionWrapper connectionWrapper) {
        connectionWrappers.remove(connectionWrapper);
    }

    public static void clearConnectionWrappers() {
        connectionWrappers.clear();
    }

    public static ITsPioTServiceWrapper connectTsPioTService(Context context) throws TsPioTServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        tsPioTService.connectService(context, false);
        return tsPioTService;
    }

    public static void disconnectTsPioTService() throws TsPioTServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        tsPioTService.disconnectService();
    }

    public static void processException(Exception exc) throws TsPioTServiceException {
        if (exc instanceof DeadObjectException) {
            tsPioTService.reconnectService();
            throw new ServiceNotConnectedException(exc);
        } else if (exc instanceof RuntimeException) {
            throw new TsPioTServiceRuntimeException(exc);
        }
        exc.printStackTrace();
    }
}
