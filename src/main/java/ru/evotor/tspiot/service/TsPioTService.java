package ru.evotor.tspiot.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.evotor.tspiot.ITsPioTService;
import ru.evotor.tspiot.TsPioTServiceConnector;
import ru.evotor.tspiot.exceptions.NullContextException;
import ru.evotor.tspiot.exceptions.ServiceAlreadyConnectedException;
import ru.evotor.tspiot.exceptions.TsPioTErrorHolderException;
import ru.evotor.tspiot.exceptions.TsPioTServiceOperationOnMainThreadException;
import ru.evotor.tspiot.exceptions.base.TsPioTServiceException;
import ru.evotor.tspiot.exceptions.UnknownException;
import ru.evotor.tspiot.model.MarkingCode;
import ru.evotor.tspiot.result.model.CodesCheckResult;
import ru.evotor.tspiot.result.model.KktInfo;
import ru.evotor.tspiot.result.TsPioTError;
import ru.evotor.tspiot.result.TsPioTResult;

public class TsPioTService implements ITsPioTServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to TsPioTService failed";

    private Context context;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ITsPioTService service;

    private volatile boolean serviceConnected = false;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ITsPioTService.Stub.asInterface(iBinder);
            serviceConnected = true;

            for(ITsPioTConnectionWrapper connectionWrapper : TsPioTServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onTsPioTServiceConnected(TsPioTService.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
            serviceConnected = false;
            context = null;

            for(ITsPioTConnectionWrapper connectionWrapper : TsPioTServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onTsPioTServiceDisconnected();
            }
        }
    };

    public TsPioTService() { }

    public synchronized void connectService(Context userContext, boolean force) throws ServiceAlreadyConnectedException, NullContextException {
        if (!force && service != null) {
            throw new ServiceAlreadyConnectedException();
        }

        if (userContext == null) {
            throw new NullContextException();
        }

        executor.execute(() -> {
            context = userContext;
            Intent pr = new Intent(TsPioTServiceConnector.ACTION_TSPIOT_SERVICE);
            pr.setPackage(TsPioTServiceConnector.TARGET_PACKAGE);
            pr.setClassName(TsPioTServiceConnector.TARGET_PACKAGE, TsPioTServiceConnector.TARGET_CLASS_NAME);
            serviceConnected = false;

            boolean serviceBound = context.bindService(pr, serviceConnection, Service.BIND_AUTO_CREATE);
            if (!serviceBound) {
                serviceConnected = false;
            }
        });
    }

    public synchronized void reconnectService() throws NullContextException {
        if (context == null) {
            throw new NullContextException();
        }

        try {
            connectService(context, true);
        } catch (ServiceAlreadyConnectedException ignored) { }
    }

    public synchronized void disconnectService() throws NullContextException {
        if (context == null) {
            throw new NullContextException();
        }

        executor.execute(() -> {
            context.unbindService(serviceConnection);
            service = null;
            serviceConnected = false;
            context = null;

            for(ITsPioTConnectionWrapper connectionWrapper : TsPioTServiceConnector.getConnectionWrappers()) {
                connectionWrapper.onTsPioTServiceDisconnected();
            }
        });
    }

    public boolean getServiceConnected() { return serviceConnected; }

    /** Метод для получения информации о драйвере ТС ПИоТ */
    @SuppressWarnings("rawtypes")
    @Override
    public KktInfo getKktInfo() throws TsPioTServiceException {
        TsPioTServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            TsPioTResult result = service.getKktInfo();
            Parcelable data = result.getData();

            if (data != null) {
                return (KktInfo) data;
            } else {
                TsPioTError error = result.getError();

                if (error != null) {
                    throw new TsPioTErrorHolderException(error.getError());
                } else {
                    throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
                }
            }
        } catch (RemoteException | RuntimeException ex) {
            TsPioTServiceConnector.processException(ex);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    /** Метод проверки марок */
    @SuppressWarnings("rawtypes")
    @Override
    public CodesCheckResult getMarkedProductsInfo(
            @NonNull List<MarkingCode> codes,
            @Nullable String userUuid
    ) throws TsPioTServiceException {
        TsPioTServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            TsPioTResult result = service.getMarkedProductsInfo(codes, userUuid);
            Parcelable data = result.getData();

            if (data != null) {
                return (CodesCheckResult) data;
            } else {
                TsPioTError error = result.getError();

                if (error != null) {
                    throw new TsPioTErrorHolderException(error.getError());
                } else {
                    throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
                }
            }
        } catch (RemoteException | RuntimeException ex) {
            TsPioTServiceConnector.processException(ex);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }
}
