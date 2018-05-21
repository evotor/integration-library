package ru.evotor.devices.commons.exception;

/**
 * Нет подключения к DeviceService
 */
public class ServiceNotConnectedException extends DeviceServiceException {
    public ServiceNotConnectedException(String message) {
        super(message);
    }

    public ServiceNotConnectedException(Exception e) {
        super(e);
    }

}
