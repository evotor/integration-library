package ru.evotor.devices.commons.exception.error_extension;


import android.os.Parcel;

public class UsbHubStateErrorExtension extends AbstractErrorExtension {

    private final UsbHubState state;

    public UsbHubStateErrorExtension(UsbHubState state) {
        this.state = state;
    }

    public UsbHubStateErrorExtension(Parcel parcel) {
        UsbHubState state;
        try {
            state = UsbHubState.valueOf(parcel.readString());
        } catch (IllegalArgumentException exc) {
            state = null;
        }
        this.state = state;
    }

    @Override
    public void writeToParcel(Parcel parcel) {
        parcel.writeString(state.name());
    }

    public UsbHubState getState() {
        return state;
    }

    public enum UsbHubState {

        /**
         * ожидаем инициализации хотя бы одного устройства в течении некоторого интервала времени
         */
        WAITING_FOR_DEVICE_BEFORE_RELOAD,

        /**
         * usb-хаб перезагружается
         */
        RELOADING,

        /**
         * usb-хаб перезагружен, ожидаем инициализации хотя бы одного устройства в течении некоторого интервала времени
         */
        WAITING_FOR_DEVICE_AFTER_RELOAD,

        /**
         * usb-хаб перезагружен, устройства не появились на шине за установленный период ожидания
         */
        HARDWARE_ERROR
    }
}
