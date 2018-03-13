package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public interface ICashDrawerServiceWrapper {
    void openAllCashDrawers() throws DeviceServiceException;

    void openCashDrawer(int deviceId) throws DeviceServiceException;
}
