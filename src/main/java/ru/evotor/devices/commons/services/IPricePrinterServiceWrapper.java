package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public interface IPricePrinterServiceWrapper {
    void beforePrintPrices(int deviceId) throws DeviceServiceException;

    void afterPrintPrices(int deviceId) throws DeviceServiceException;

    void printPrice(int deviceId, String name, String price, String barcode, String code) throws DeviceServiceException;
}
