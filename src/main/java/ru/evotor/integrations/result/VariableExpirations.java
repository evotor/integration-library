package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;

/** Вариативный срок годности */
public class VariableExpirations implements Parcelable {

    private VariableExpiration[] expirations;

    private VariableExpirations(Parcel parcel) {
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
}
