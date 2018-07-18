package ru.evotor.devices.commons.exception;

public class DeviceServiceRuntimeException extends DeviceServiceException {

    public DeviceServiceRuntimeException(RuntimeException exc) {
        super(exc);
    }

}
