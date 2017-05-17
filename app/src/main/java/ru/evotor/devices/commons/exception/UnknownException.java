package ru.evotor.devices.commons.exception;

/**
 * Неизвестная ошибка
 */
public class UnknownException extends DeviceServiceException {
    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(Exception e) {
        super(e);
    }

}
