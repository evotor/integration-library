package ru.evotor.devices.commons.exception.error_extension;


import android.os.Parcel;

public class DriverExceptionErrorExtension extends AbstractErrorExtension {

    private final String driverMessage;

    public DriverExceptionErrorExtension(String driverMessage) {
        this.driverMessage = driverMessage;
    }

    public DriverExceptionErrorExtension(Parcel parcel) {
        this(parcel.readString());
    }

    public String getDriverMessage() {
        return driverMessage;
    }

    @Override
    public void writeToParcel(Parcel parcel) {
        parcel.writeString(driverMessage);
    }
}
