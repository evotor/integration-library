package ru.evotor.devices.commons.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import ru.evotor.devices.commons.DeviceServiceConnector;
import ru.evotor.devices.commons.IDeviceService;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.result.ResultDeviceInfo;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultIntArray;
import ru.evotor.devices.commons.service.DeviceInfo;

import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;
import static ru.evotor.devices.commons.InternalConstants.ACTION_DEVICE_SERVICE;

public class DeviceService extends AbstractService implements IDeviceServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected Context context;

    protected volatile Boolean serviceConnected = null;
    protected IDeviceService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = IDeviceService.Stub.asInterface(binder);
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

    public DeviceService() {
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
                    Intent pr = new Intent(ACTION_DEVICE_SERVICE);
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
    public int[] getKKMs() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getKKMs();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultKKMIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultKKMIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getPaySystems() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getPaySystems();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultPaySystemIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultPaySystemIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getPricePrinters() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getPricePrinters();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultPricePrinterIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultPricePrinterIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getCashDrawers() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getCashDrawers();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultCashDrawerIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultCashDrawerIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getScales() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getScales();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultScalesIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultScalesIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getScanners() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getScanners();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getAllDevices() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getAllDevices();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public DeviceInfo getDeviceInfo(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultDeviceInfo result = service.getDeviceInfo(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getPermanentIdFromDeviceId(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getPermanentIdFromDeviceId(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDeviceIdFromPermanentId(int permanentId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDeviceIdFromPermanentId(permanentId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int[] getPrinters() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultIntArray result = service.getPrinters();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getDefaultPrinterIndex() throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getDefaultPrinterIndex();
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

}
