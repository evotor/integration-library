package ru.evotor.devices.commons.services;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.paysystem.PayInfo;
import ru.evotor.devices.commons.paysystem.PayResult;

public interface IPaySystemServiceWrapper {
    PayResult payment(int deviceId, PayInfo payinfo) throws DeviceServiceException;

    PayResult cancelPayment(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException;

    PayResult payback(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException;

    PayResult cancelPayback(int deviceId, PayInfo payinfo, String rrn) throws DeviceServiceException;

    PayResult closeSession(int deviceId) throws DeviceServiceException;

    void openServiceMenu(int deviceId) throws DeviceServiceException;

    String getBankName(int deviceId) throws DeviceServiceException;

    int getTerminalNumber(int deviceId) throws DeviceServiceException;

    String getTerminalID(int deviceId) throws DeviceServiceException;

    String getMerchNumber(int deviceId) throws DeviceServiceException;

    String getMerchCategoryCode(int deviceId) throws DeviceServiceException;

    String getMerchEngName(int deviceId) throws DeviceServiceException;

    String getCashier(int deviceId) throws DeviceServiceException;

    String getServerIP(int deviceId) throws DeviceServiceException;

    boolean isNotNeedRRN(int deviceId) throws DeviceServiceException;
}
