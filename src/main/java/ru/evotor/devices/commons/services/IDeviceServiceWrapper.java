package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.service.DeviceInfo;

public interface IDeviceServiceWrapper {
    int[] getKKMs() throws DeviceServiceException;

    int getDefaultKKMIndex() throws DeviceServiceException;

    int[] getPaySystems() throws DeviceServiceException;

    int getDefaultPaySystemIndex() throws DeviceServiceException;

    int[] getPricePrinters() throws DeviceServiceException;

    int getDefaultPricePrinterIndex() throws DeviceServiceException;

    int[] getCashDrawers() throws DeviceServiceException;

    int getDefaultCashDrawerIndex() throws DeviceServiceException;

    int[] getScales() throws DeviceServiceException;

    int getDefaultScalesIndex() throws DeviceServiceException;

    int[] getScanners() throws DeviceServiceException;

    int[] getAllDevices() throws DeviceServiceException;

    DeviceInfo getDeviceInfo(int deviceId) throws DeviceServiceException;

    int getPermanentIdFromDeviceId(int deviceId) throws DeviceServiceException;

    int getDeviceIdFromPermanentId(int permanentId) throws DeviceServiceException;

    int[] getPrinters() throws DeviceServiceException;

    int getDefaultPrinterIndex() throws DeviceServiceException;
}
