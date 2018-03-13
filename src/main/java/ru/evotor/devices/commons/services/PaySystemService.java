package ru.evotor.devices.commons.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import ru.evotor.devices.commons.DeviceServiceConnector;
import ru.evotor.devices.commons.IPaySystemService;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.paysystem.PayInfo;
import ru.evotor.devices.commons.paysystem.PayResult;
import ru.evotor.devices.commons.result.ResultBoolean;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultPayResult;
import ru.evotor.devices.commons.result.ResultString;
import ru.evotor.devices.commons.result.ResultVoid;

import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;
import static ru.evotor.devices.commons.InternalConstants.ACTION_PAY_SYSTEM_SERVICE;


public class PaySystemService extends AbstractService implements IPaySystemServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected Context context;

    protected volatile Boolean serviceConnected = null;
    protected IPaySystemService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = IPaySystemService.Stub.asInterface(binder);
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

    public PaySystemService() {
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
                    Intent pr = new Intent(ACTION_PAY_SYSTEM_SERVICE);
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
    public PayResult payment(int deviceId, PayInfo payinfo) throws DeviceServiceException {
        try {
            ResultPayResult result = service.payment(deviceId, payinfo);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public PayResult cancelPayment(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultPayResult result = service.cancelPayment(deviceId, payinfo, rrn);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public PayResult payback(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultPayResult result = service.payback(deviceId, payinfo, rrn);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public PayResult cancelPayback(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultPayResult result = service.cancelPayback(deviceId, payinfo, rrn);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public PayResult closeSession(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultPayResult result = service.closeSession(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public void openServiceMenu(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultVoid result = service.openServiceMenu(deviceId);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getBankName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultString result = service.getBankName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getTerminalNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultInt result = service.getTerminalNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getTerminalID(int deviceId) throws DeviceServiceException {
        try {
            ResultString result = service.getTerminalID(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getMerchNumber(int deviceId) throws DeviceServiceException {
        try {
            ResultString result = service.getMerchNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getMerchCategoryCode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultString result = service.getMerchCategoryCode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getMerchEngName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultString result = service.getMerchEngName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCashier(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultString result = service.getCashier(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getServerIP(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultString result = service.getServerIP(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean isNotNeedRRN(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();

        try {
            ResultBoolean result = service.isNotNeedRRN(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

}
