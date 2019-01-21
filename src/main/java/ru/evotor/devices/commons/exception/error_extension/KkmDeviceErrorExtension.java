package ru.evotor.devices.commons.exception.error_extension;

import android.os.Parcel;

public class KkmDeviceErrorExtension extends AbstractErrorExtension {

    private final int errorCode;
    private final String errorMessage;

    public KkmDeviceErrorExtension(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public KkmDeviceErrorExtension(Parcel parcel) {
        super(parcel);
        this.errorCode = parcel.readInt();
        this.errorMessage = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel) {
        parcel.writeInt(errorCode);
        parcel.writeString(errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
