package ru.evotor.devices.commons.result;

import android.os.Parcel;

import ru.evotor.devices.commons.exception.DeviceNotFoundException;
import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.exception.DriverException;
import ru.evotor.devices.commons.exception.NoPermanentInfoException;
import ru.evotor.devices.commons.exception.ServiceNotConnectedException;
import ru.evotor.devices.commons.exception.UnknownException;
import ru.evotor.devices.commons.exception.UsbHubProblemException;
import ru.evotor.devices.commons.exception.error_extension.AbstractErrorExtension;
import ru.evotor.devices.commons.exception.error_extension.DriverExceptionErrorExtension;
import ru.evotor.devices.commons.exception.error_extension.UsbHubStateErrorExtension;

public abstract class ErrorDescriptionPacker {
    private static final int UNKNOWN = -1;
    private static final int SERVICE_NOT_FOUND = -2;
    private static final int DEVICE_NOT_FOUND = -3;
    private static final int USB_HUB_PROBLEM = -4;
    private static final int DRIVER_EXCEPTION = -5;
    private static final int NO_PERMANENT_INFO = -6;

    private ErrorDescriptionPacker() {

    }

    public static ErrorDescription packException(DeviceServiceException exc) {
        if (exc == null) {
            return new ErrorDescription(0, null);
        }
        if (exc instanceof ServiceNotConnectedException) {
            return new ErrorDescription(SERVICE_NOT_FOUND, exc.getMessage());
        } else if (exc instanceof DeviceNotFoundException) {
            return new ErrorDescription(DEVICE_NOT_FOUND, exc.getMessage());
        } else if (exc instanceof UsbHubProblemException) {
            return new ErrorDescription(USB_HUB_PROBLEM, exc.getMessage(), new UsbHubStateErrorExtension(((UsbHubProblemException) exc).getUsbHubState()));
        } else if (exc instanceof DriverException) {
            return new ErrorDescription(DRIVER_EXCEPTION, exc.getMessage(), new DriverExceptionErrorExtension(((DriverException) exc).getDriverMessage()));
        } else if (exc instanceof NoPermanentInfoException) {
            return new ErrorDescription(NO_PERMANENT_INFO, exc.getMessage());
        } else {
            return new ErrorDescription(UNKNOWN, exc.getMessage());
        }
    }

    public static DeviceServiceException unpackException(ErrorDescription errorDescription) {
        if (errorDescription == null) {
            return null;
        }

        int code = errorDescription.getErrorCode();
        String errorUserDescription = errorDescription.getErrorUserDescription();
        if (code == 0) {
            return null;
        }

        switch (code) {

            case SERVICE_NOT_FOUND:
                return new ServiceNotConnectedException(errorUserDescription);

            case DEVICE_NOT_FOUND:
                return new DeviceNotFoundException();

            case USB_HUB_PROBLEM:
                AbstractErrorExtension hubProblemErrorExtension = errorDescription.getErrorExtension();
                if (hubProblemErrorExtension instanceof UsbHubStateErrorExtension) {
                    return new UsbHubProblemException(((UsbHubStateErrorExtension) hubProblemErrorExtension).getState());
                } else {
                    return new UnknownException(errorUserDescription);
                }

            case DRIVER_EXCEPTION:
                AbstractErrorExtension driverExceptionErrorExtension = errorDescription.getErrorExtension();
                if (driverExceptionErrorExtension instanceof DriverExceptionErrorExtension) {
                    return new DriverException(((DriverExceptionErrorExtension) driverExceptionErrorExtension).getDriverMessage());
                } else {
                    return new UnknownException(errorUserDescription);
                }

            case NO_PERMANENT_INFO:
                return new NoPermanentInfoException();

            default:
                return new UnknownException(errorUserDescription);
        }
    }

    public static AbstractErrorExtension createErrorExtension(int errorCode, Parcel parcel) {
        switch (errorCode) {
            case USB_HUB_PROBLEM:
                return new UsbHubStateErrorExtension(parcel);
            case DRIVER_EXCEPTION:
                return new DriverExceptionErrorExtension(parcel);
            default:
                return null;
        }
    }
}