package ru.evotor.devices.commons.exception;

/**
 * Ошибка при выполнении метода в драйвере устройства
 */
public class DriverException extends DeviceServiceException {

    private final String driverMessage;

    public DriverException(String driverMessage) {
        super();
        this.driverMessage = driverMessage;
    }

    public String getDriverMessage() {
        return driverMessage;
    }
}
