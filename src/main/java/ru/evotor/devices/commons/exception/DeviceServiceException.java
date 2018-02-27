package ru.evotor.devices.commons.exception;

/**
 * Родительский класс всех возможных исключений, которые могут быть выброшены при работе с DeviceServiceConnector
 */
public abstract class DeviceServiceException extends Exception {

    public DeviceServiceException() {
        super();
    }

    public DeviceServiceException(String message) {
        super(message);
    }

    public DeviceServiceException(Exception e) {
        super(e);
    }

}
