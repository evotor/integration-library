package ru.evotor.devices.commons.exception;

import ru.evotor.devices.commons.exception.error_extension.UsbHubStateErrorExtension;

/**
 * Не найдено указанное устройство, и в системе отсутвуют USB-устройства.
 * Возможно, это проблема USB-хаба, которая может быть решена программной перезагрузкой.
 * Текущий статус этой перезагрузки можно посмотреть в поле {@link UsbHubStateErrorExtension.UsbHubState usbHubState}
 */
public class UsbHubProblemException extends DeviceServiceException {

    private final UsbHubStateErrorExtension.UsbHubState usbHubState;

    public UsbHubProblemException(UsbHubStateErrorExtension.UsbHubState usbHubState) {
        super();
        this.usbHubState = usbHubState;
    }

    public UsbHubStateErrorExtension.UsbHubState getUsbHubState() {
        return usbHubState;
    }
}
