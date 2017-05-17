package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.scales.Weight;

public interface IScalesServiceWrapper {
    Weight getWeight(int deviceId) throws DeviceServiceException;
}
