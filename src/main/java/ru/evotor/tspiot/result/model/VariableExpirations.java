package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.Utils;

/** Вариативный срок годности */
public class VariableExpirations implements Parcelable {

    /** Версия VariableExpirations */
    private final static int VERSION = 1;

    private VariableExpiration[] expirations;

    private VariableExpirations(Parcel parcel) {
        int version = parcel.readInt();
        parcel.readTypedArray(expirations, VariableExpiration.CREATOR);
    }

    public VariableExpirations(VariableExpiration[] expirations) {
        this.expirations = expirations;
    }

    public VariableExpiration[] getExpirations() { return expirations; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeTypedArray(expirations, flags);
    }

    public static Creator<VariableExpirations> CREATOR = new Creator<VariableExpirations>() {
        @Override
        public VariableExpirations createFromParcel(Parcel parcel) {
            return new VariableExpirations(parcel);
        }

        @Override
        public VariableExpirations[] newArray(int i) {
            return new VariableExpirations[i];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "VariableExpirations: " + Utils.toString(expirations);
    }
}
