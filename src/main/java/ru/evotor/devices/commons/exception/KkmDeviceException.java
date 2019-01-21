package ru.evotor.devices.commons.exception;

public class KkmDeviceException extends DeviceServiceException {

    private final int errorCode;

    public KkmDeviceException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}

