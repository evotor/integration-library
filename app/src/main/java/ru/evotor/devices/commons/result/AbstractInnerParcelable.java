package ru.evotor.devices.commons.result;

import android.os.Parcel;

public abstract class AbstractInnerParcelable {
    public AbstractInnerParcelable() {
    }

    public AbstractInnerParcelable(Parcel parcel) {
    }

    public abstract void writeToParcel(Parcel parcel);
}
