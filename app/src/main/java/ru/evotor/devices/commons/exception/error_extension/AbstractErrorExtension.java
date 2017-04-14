package ru.evotor.devices.commons.exception.error_extension;

import android.os.Parcel;

public abstract class AbstractErrorExtension {
    public AbstractErrorExtension() {
    }

    public AbstractErrorExtension(Parcel parcel) {
    }

    public abstract void writeToParcel(Parcel parcel);
}
