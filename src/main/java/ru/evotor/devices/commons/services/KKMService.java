package ru.evotor.devices.commons.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import ru.evotor.devices.commons.DeviceServiceConnector;
import ru.evotor.devices.commons.IKKMService;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DeviceServiceOperationOnMainThreadException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.result.ResultBoolean;
import ru.evotor.devices.commons.result.ResultDouble;
import ru.evotor.devices.commons.result.ResultInt;
import ru.evotor.devices.commons.result.ResultLong;
import ru.evotor.devices.commons.result.ResultString;
import ru.evotor.devices.commons.result.ResultVoid;
import ru.evotor.devices.commons.service.FfdNumber;

import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_CLASS_NAME;
import static ru.evotor.devices.commons.DeviceServiceConnector.TARGET_PACKAGE;
import static ru.evotor.devices.commons.InternalConstants.ACTION_KKM_SERVICE;

public class KKMService extends AbstractService implements IKKMServiceWrapper {

    public static final String UNKNOWN_EXCEPTION_TEXT = "Request to DeviceService failed";

    protected Context context;

    protected volatile Boolean serviceConnected = null;
    protected IKKMService service;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = IKKMService.Stub.asInterface(binder);
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

    public KKMService() {
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
                    Intent pr = new Intent(ACTION_KKM_SERVICE);
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
    public boolean is54fzDevice(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.is54fzDevice(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public FfdNumber getCachedFfdKKMNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedFfdKKMNumber(deviceId);
            int ordinal = result.getResult();
            if (ordinal >= 0 && ordinal < FfdNumber.values().length) {
                return FfdNumber.values()[ordinal];
            }
            return FfdNumber.UNKNOWN;
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public FfdNumber getCachedFfdFnNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedFfdFnNumber(deviceId);
            int ordinal = result.getResult();
            if (ordinal >= 0 && ordinal < FfdNumber.values().length) {
                return FfdNumber.values()[ordinal];
            }
            return FfdNumber.UNKNOWN;
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedKkmSerialNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedKkmSerialNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedKkmInn(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedKkmInn(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedEKLZSerialNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedEKLZSerialNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedEKLZFlags(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedEKLZFlags(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedEKLZKPKNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedEKLZKPKNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedEKLZKPK(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedEKLZKPK(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedDeviceDescription(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedDeviceDescription(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedNotSendedDocsCount(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedNotSendedDocsCount(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedOfdAddress(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedOfdAddress(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedOfdPort(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedOfdPort(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long getCachedOfdDocumentDate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.getCachedOfdDocumentDate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long getCachedOfdDocumentTime(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.getCachedOfdDocumentTime(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedNetworkError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedNetworkError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedFnError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedFnError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int getCachedOfdError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.getCachedOfdError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_LicenseValid(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_LicenseValid(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_LicenseExpiredDate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_LicenseExpiredDate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Version(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Version(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_DriverName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_DriverName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_DeviceEnabled(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_DeviceEnabled(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DeviceEnabled(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DeviceEnabled(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ResultCode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ResultCode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ResultDescription(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ResultDescription(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BadParam(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BadParam(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_BadParamDescription(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_BadParamDescription(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_DeviceSettings(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_DeviceSettings(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DeviceSettings(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DeviceSettings(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_DeviceSingleSetting(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_DeviceSingleSetting(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DeviceSingleStringSetting(int deviceId, String var1, String var2) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DeviceSingleStringSetting(deviceId, var1, var2);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DeviceSingleIntSetting(int deviceId, String var1, int var2) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DeviceSingleIntSetting(deviceId, var1, var2);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DeviceSingleDoubleSetting(int deviceId, String var1, double var2) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DeviceSingleDoubleSetting(deviceId, var1, var2);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_DeviceSingleSettingMapping(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_DeviceSingleSettingMapping(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ShowProperties(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ShowProperties(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ApplySingleSettings(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ApplySingleSettings(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ResetSingleSettings(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ResetSingleSettings(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Caption(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Caption(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Caption(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Caption(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CaptionPurpose(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CaptionPurpose(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CaptionPurpose(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CaptionPurpose(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_CaptionIsSupported(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_CaptionIsSupported(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_CaptionName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_CaptionName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Value(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Value(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Value(int deviceId, double var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Value(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ValuePurpose(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ValuePurpose(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ValuePurpose(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ValuePurpose(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_ValueIsSupported(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_ValueIsSupported(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ValueName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ValueName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ValueMapping(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ValueMapping(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CharLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CharLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_SerialNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_SerialNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_SerialNumber(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_SerialNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_Time(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_Time(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Time(int deviceId, long var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Time(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_Date(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_Date(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Date(int deviceId, long var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Date(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_Fiscal(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_Fiscal(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_TestMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_TestMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TestMode(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TestMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_EnableCheckSumm(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_EnableCheckSumm(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_EnableCheckSumm(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_EnableCheckSumm(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_UserPassword(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_UserPassword(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_UserPassword(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_UserPassword(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Mode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Mode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Mode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Mode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Alignment(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Alignment(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Alignment(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Alignment(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_TextWrap(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_TextWrap(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TextWrap(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TextWrap(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Barcode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Barcode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Barcode(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Barcode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrintBarcodeText(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrintBarcodeText(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PrintBarcodeText(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PrintBarcodeText(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SlipDocOrientation(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SlipDocOrientation(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_SlipDocOrientation(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_SlipDocOrientation(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Scale(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Scale(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Scale(int deviceId, double var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Scale(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Height(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Height(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Height(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Height(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_TypeClose(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_TypeClose(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TypeClose(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TypeClose(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Summ(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Summ(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Summ(int deviceId, double var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Summ(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CheckType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CheckType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CheckState(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CheckState(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CheckType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CheckType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CheckNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CheckNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CheckNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CheckNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_RegisterNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_RegisterNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_RegisterNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_RegisterNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DocNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DocNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DocNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DocNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_SessionOpened(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_SessionOpened(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Session(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Session(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Session(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Session(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_CheckPaperPresent(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_CheckPaperPresent(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_ControlPaperPresent(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_ControlPaperPresent(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PLUNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PLUNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PLUNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PLUNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Name(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Name(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Name(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Name(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Price(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Price(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Price(int deviceId, double var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Price(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Quantity(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Quantity(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Quantity(int deviceId, double var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Quantity(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Department(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Department(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Department(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Department(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DiscountType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DiscountType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DiscountType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DiscountType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReportType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReportType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReportType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReportType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_InfoLine(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_InfoLine(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Model(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Model(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_ClearFlag(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_ClearFlag(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ClearFlag(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ClearFlag(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_FileName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_FileName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileName(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileName(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_INN(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_INN(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_INN(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_INN(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_MachineNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_MachineNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_MachineNumber(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_MachineNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_License(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_License(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_License(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_License(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_LicenseNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_LicenseNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_LicenseNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_LicenseNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Table(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Table(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Table(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Table(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Row(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Row(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Row(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Row(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Field(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Field(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Field(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Field(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FieldType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FieldType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FieldType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FieldType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_CommandBuffer(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_CommandBuffer(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CommandBuffer(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CommandBuffer(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_AnswerBuffer(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_AnswerBuffer(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_DateEnd(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_DateEnd(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DateEnd(int deviceId, long var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DateEnd(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SessionEnd(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SessionEnd(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_SessionEnd(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_SessionEnd(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_EKLZFlags(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_EKLZFlags(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_EKLZKPKNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_EKLZKPKNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_EKLZKPKNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_EKLZKPKNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_UnitType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_UnitType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_UnitType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_UnitType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PictureNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PictureNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PictureNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PictureNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_LeftMargin(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_LeftMargin(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_LeftMargin(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_LeftMargin(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Memory(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Memory(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PictureState(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PictureState(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Width(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Width(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Width(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Width(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Operator(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Operator(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Operator(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Operator(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontBold(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontBold(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontBold(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontBold(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontItalic(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontItalic(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontItalic(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontItalic(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontNegative(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontNegative(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontNegative(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontNegative(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontUnderline(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontUnderline(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontUnderline(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontUnderline(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontDblHeight(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontDblHeight(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontDblHeight(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontDblHeight(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FontDblWidth(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FontDblWidth(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FontDblWidth(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FontDblWidth(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PrintPurpose(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PrintPurpose(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PrintPurpose(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PrintPurpose(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReceiptFont(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReceiptFont(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReceiptFont(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReceiptFont(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReceiptFontHeight(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReceiptFontHeight(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReceiptFontHeight(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReceiptFontHeight(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReceiptBrightness(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReceiptBrightness(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReceiptBrightness(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReceiptBrightness(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReceiptLinespacing(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReceiptLinespacing(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReceiptLinespacing(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReceiptLinespacing(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_JournalFont(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_JournalFont(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JournalFont(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JournalFont(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_JournalFontHeight(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_JournalFontHeight(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JournalFontHeight(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JournalFontHeight(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_JournalBrightness(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_JournalBrightness(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JournalBrightness(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JournalBrightness(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_JournalLinespacing(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_JournalLinespacing(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JournalLinespacing(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JournalLinespacing(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_SummPointPosition(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_SummPointPosition(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SummPointPosition(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SummPointPosition(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TaxNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TaxNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_TaxNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_TaxNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodePrintType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodePrintType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodePrintType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodePrintType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeControlCode(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeControlCode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeControlCode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeControlCode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeCorrection(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeCorrection(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeCorrection(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeCorrection(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeEncoding(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeEncoding(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeEncoding(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeEncoding(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeEncodingMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeEncodingMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeEncodingMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeEncodingMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FeedValue(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FeedValue(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FeedValue(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FeedValue(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_ClsPtr(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_ClsPtr(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PixelLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PixelLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_RcpPixelLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_RcpPixelLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JrnPixelLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JrnPixelLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SlipPixelLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SlipPixelLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_RcpCharLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_RcpCharLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_JrnCharLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_JrnCharLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SlipCharLineLength(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SlipCharLineLength(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Count(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Count(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Count(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Count(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_SlotNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_SlotNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_SlotNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_SlotNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_DrawerOpened(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_DrawerOpened(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_CoverOpened(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_CoverOpened(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BatteryLow(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BatteryLow(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_VerHi(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_VerHi(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_VerLo(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_VerLo(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Build(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Build(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Codepage(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Codepage(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Remainder(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Remainder(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_Change(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_Change(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_LogicalNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_LogicalNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_LogicalNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_LogicalNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_OperationType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_OperationType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_OperationType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_OperationType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CounterType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CounterType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CounterType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CounterType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_PowerSupplyValue(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_PowerSupplyValue(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PowerSupplyState(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PowerSupplyState(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PowerSupplyType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PowerSupplyType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PowerSupplyType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PowerSupplyType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_StepCounterType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_StepCounterType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_StepCounterType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_StepCounterType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Destination(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Destination(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Destination(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Destination(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodePixelProportions(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodePixelProportions(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodePixelProportions(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodePixelProportions(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeProportions(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeProportions(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeProportions(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeProportions(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeColumns(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeColumns(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeColumns(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeColumns(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeRows(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeRows(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeRows(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeRows(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodePackingMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodePackingMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodePackingMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodePackingMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeUseProportions(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeUseProportions(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeUseProportions(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeUseProportions(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeUseRows(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeUseRows(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeUseRows(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeUseRows(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeUseColumns(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeUseColumns(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeUseColumns(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeUseColumns(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeUseCorrection(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeUseCorrection(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeUseCorrection(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeUseCorrection(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeUseCodeWords(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeUseCodeWords(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeUseCodeWords(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeUseCodeWords(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeInvert(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeInvert(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeInvert(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeInvert(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeDeferredPrint(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeDeferredPrint(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_BarcodeDeferredPrint(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_BarcodeDeferredPrint(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DrawerOnTimeout(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DrawerOnTimeout(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DrawerOnTimeout(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DrawerOnTimeout(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DrawerOffTimeout(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DrawerOffTimeout(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DrawerOffTimeout(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DrawerOffTimeout(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DrawerOnQuantity(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DrawerOnQuantity(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DrawerOnQuantity(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DrawerOnQuantity(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Frequency(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Frequency(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Frequency(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Frequency(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Duration(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Duration(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_Duration(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_Duration(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Directory(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Directory(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Directory(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Directory(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileSize(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileSize(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FileSize(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FileSize(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileOpenType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileOpenType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FileOpenType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FileOpenType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileOpenMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileOpenMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FileOpenMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FileOpenMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileOffset(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileOffset(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FileOffset(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FileOffset(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FileReadSize(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FileReadSize(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FileReadSize(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FileReadSize(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ResetMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ResetMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Beep(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Beep(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Sound(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Sound(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenDrawer(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenDrawer(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int AdvancedOpenDrawer(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.AdvancedOpenDrawer(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int FullCut(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.FullCut(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PartialCut(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PartialCut(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Feed(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Feed(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenDirectory(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenDirectory(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReadDirectory(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReadDirectory(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CloseDirectory(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CloseDirectory(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenFile(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenFile(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CloseFile(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CloseFile(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int DeleteFileFromSD(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.DeleteFileFromSD(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int WriteFileToSD(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.WriteFileToSD(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReadFile(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReadFile(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetRegister(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetRegister(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetRange(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetRange(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetSumm(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetSumm(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenSession(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenSession(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CashIncome(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CashIncome(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CashOutcome(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CashOutcome(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Report(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Report(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int NewDocument(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.NewDocument(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenCheck(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenCheck(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Registration(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Registration(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Annulate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Annulate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Return(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Return(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Buy(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Buy(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BuyReturn(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BuyReturn(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BuyAnnulate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BuyAnnulate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Storno(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Storno(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Discount(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Discount(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Charge(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Charge(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ResetChargeDiscount(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ResetChargeDiscount(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Payment(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Payment(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int StornoPayment(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.StornoPayment(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CancelCheck(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CancelCheck(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CloseCheck(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CloseCheck(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SummTax(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SummTax(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int StornoTax(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.StornoTax(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintString(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintString(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int AddTextField(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.AddTextField(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintFormattedText(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintFormattedText(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintHeader(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintHeader(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintFooter(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintFooter(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BeginDocument(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BeginDocument(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EndDocument(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EndDocument(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintLastCheckCopy(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintLastCheckCopy(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintBarcode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintBarcode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintPicture(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintPicture(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetPictureArrayStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetPictureArrayStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetPictureStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetPictureStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintPictureByNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintPictureByNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int AddPicture(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.AddPicture(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int AddPictureFromFile(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.AddPictureFromFile(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int DeleteLastPicture(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.DeleteLastPicture(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ClearPictureArray(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ClearPictureArray(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetPicture(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetPicture(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ClearBarcodeArray(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ClearBarcodeArray(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetBarcode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetBarcode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PrintBarcodeByNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PrintBarcodeByNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BeginReport(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BeginReport(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetRecord(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetRecord(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EndReport(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EndReport(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BeginAdd(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BeginAdd(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetRecord(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetRecord(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EndAdd(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EndAdd(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetCaption(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetCaption(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetCaption(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetCaption(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetValue(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetValue(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetValue(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetValue(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetTableField(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetTableField(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetTableField(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetTableField(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Fiscalization(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Fiscalization(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ResetSummary(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ResetSummary(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetDate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetDate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetTime(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetTime(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetLicense(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetLicense(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetLicense(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetLicense(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetPointPosition(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetPointPosition(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int SetSerialNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.SetSerialNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int InitTables(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.InitTables(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int TechZero(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.TechZero(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int RunCommand(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.RunCommand(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int TestConnector(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.TestConnector(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int DemoPrint(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.DemoPrint(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PowerOff(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PowerOff(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ClearOutput(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ClearOutput(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int WriteData(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.WriteData(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EKLZActivate(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EKLZActivate(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EKLZCloseArchive(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EKLZCloseArchive(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EKLZGetStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EKLZGetStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_ScannerPortHandler(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_ScannerPortHandler(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ScannerMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ScannerMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ScannerMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ScannerMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PinPadMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PinPadMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_PinPadMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_PinPadMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PowerOnPinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PowerOnPinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PowerOffPinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PowerOffPinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int WritePinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.WritePinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReadPinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReadPinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_PinPadDevice(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_PinPadDevice(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ModemMode(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ModemMode(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ModemMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ModemMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PowerOnModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PowerOnModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int PowerOffModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.PowerOffModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int WriteModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.WriteModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReadModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReadModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public long get_ModemDevice(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultLong result = service.get_ModemDevice(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ReadSize(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ReadSize(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ReadSize(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ReadSize(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_NeedResultFlag(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_NeedResultFlag(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_NeedResultFlag(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_NeedResultFlag(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenPinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenPinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ClosePinPad(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ClosePinPad(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int OpenModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.OpenModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int CloseModem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.CloseModem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ModemConnectionType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ModemConnectionType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ModemConnectionType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ModemConnectionType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ModemAddress(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ModemAddress(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ModemAddress(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ModemAddress(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_ModemPort(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_ModemPort(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ModemPort(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ModemPort(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetModemStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetModemStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetPinPadStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetPinPadStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_WriteSize(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_WriteSize(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ModemStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ModemStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_ModemSignal(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_ModemSignal(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ModemOperator(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ModemOperator(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_ModemError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_ModemError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetDeviceMetrics(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetDeviceMetrics(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_DeviceDescription(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_DeviceDescription(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetCurrentMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetCurrentMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_OutOfPaper(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_OutOfPaper(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrinterConnectionFailed(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrinterConnectionFailed(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrinterMechanismError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrinterMechanismError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrinterCutMechanismError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrinterCutMechanismError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrinterOverheatError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrinterOverheatError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetCurrentStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetCurrentStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetLastSummary(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetLastSummary(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_AdvancedMode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_AdvancedMode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BottomMargin(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BottomMargin(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BottomMargin(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BottomMargin(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int EKLZGetKPK(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.EKLZGetKPK(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_EKLZKPK(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_EKLZKPK(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_BarcodeVersion(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_BarcodeVersion(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_BarcodeVersion(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_BarcodeVersion(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TaxPassword(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TaxPassword(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_TaxPassword(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_TaxPassword(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_Classifier(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_Classifier(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_Classifier(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_Classifier(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FiscalPropertyNumber(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FiscalPropertyNumber(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FiscalPropertyNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FiscalPropertyNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FiscalPropertyValue(int deviceId, String var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FiscalPropertyValue(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String get_FiscalPropertyValue(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.get_FiscalPropertyValue(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FiscalPropertyType(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FiscalPropertyType(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FiscalPropertyType(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FiscalPropertyType(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FiscalPropertyPrint(int deviceId, boolean var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FiscalPropertyPrint(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_FiscalPropertyPrint(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_FiscalPropertyPrint(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int WriteFiscalProperty(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.WriteFiscalProperty(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReadFiscalProperty(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReadFiscalProperty(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_HasNotSendedDocs(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_HasNotSendedDocs(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int RunFNCommand(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.RunFNCommand(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_CounterDimension(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_CounterDimension(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_CounterDimension(int deviceId, int var1) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_CounterDimension(deviceId, var1);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DiscountNumber(int deviceId, int i) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DiscountNumber(deviceId, i);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_DiscountNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_DiscountNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int DeleteLastBarcode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.DeleteLastBarcode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_DiscountInSession(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_DiscountInSession(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public double get_ChargeInSession(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultDouble result = service.get_ChargeInSession(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_NetworkError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_NetworkError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_OFDError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_OFDError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FNError(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FNError(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_TimeoutACK(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_TimeoutACK(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TimeoutACK(int deviceId, int i) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TimeoutACK(deviceId, i);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_TimeoutENQ(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_TimeoutENQ(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TimeoutENQ(int deviceId, int i) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TimeoutENQ(deviceId, i);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int AddBarcode(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.AddBarcode(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int GetBarcodeArrayStatus(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.GetBarcodeArrayStatus(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int Correction(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.Correction(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int ReturnCorrection(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.ReturnCorrection(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BuyCorrection(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BuyCorrection(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int BuyReturnCorrection(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.BuyReturnCorrection(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_DiscountSumm(int deviceId, double value) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_DiscountSumm(deviceId, value);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_TaxSumm(int deviceId, double value) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_TaxSumm(deviceId, value);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_FeatureOfCalculation(int deviceId, int value) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_FeatureOfCalculation(deviceId, value);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_MethodOfCalculation(int deviceId, int value) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_MethodOfCalculation(deviceId, value);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int put_PrintCheck(int deviceId, boolean b) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.put_PrintCheck(deviceId, b);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean get_PrintCheck(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.get_PrintCheck(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedFnRegNum(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedFnRegNum(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedKktNumber(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedKktNumber(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean isCachedAutonomous(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.isCachedAutonomous(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean isCachedService(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.isCachedService(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean isCachedCrypt(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.isCachedCrypt(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean isCachedInternetPay(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.isCachedInternetPay(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedFnLifePhase(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedFnLifePhase(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedTaxSystems(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedTaxSystems(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedDefaultTaxSystem(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedDefaultTaxSystem(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedOrgInfoInn(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedOrgInfoInn(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedOrgInfoAddress(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedOrgInfoAddress(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedOrgInfoName(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedOrgInfoName(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public String getCachedOfdInfoInn(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultString result = service.getCachedOfdInfoInn(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public int get_FNState(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultInt result = service.get_FNState(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public void refreshCachedValues(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultVoid result = service.refreshCachedValues(deviceId);
            result.processAnswer();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public boolean hasNewSoftwareForFlashing(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            ResultBoolean result = service.hasNewSoftwareForFlashing(deviceId);
            return result.getResult();
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }

    @Override
    public void flashAllIfAvailable(int deviceId) throws DeviceServiceException {
        DeviceServiceOperationOnMainThreadException.throwIfMainThread();
        try {
            service.flashAllIfAvailable(deviceId);
        } catch (RemoteException | RuntimeException exc) {
            DeviceServiceConnector.processException(exc);
            throw new UnknownException(UNKNOWN_EXCEPTION_TEXT);
        }
    }
}
