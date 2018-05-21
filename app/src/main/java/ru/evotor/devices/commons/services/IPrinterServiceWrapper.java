package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.printer.PrinterDocument;

public interface IPrinterServiceWrapper {
    int getAllowableSymbolsLineLength(int deviceId) throws DeviceServiceException;

    int getAllowablePixelLineLength(int deviceId) throws DeviceServiceException;

    void printDocument(int deviceId, PrinterDocument printerDocument) throws DeviceServiceException;
}
