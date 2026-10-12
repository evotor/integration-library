package ru.evotor.tspiot.result.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import ru.evotor.tspiot.Utils;

/** Вариативный срок годности */
public class VariableExpirations implements Parcelable {

    /** Версия VariableExpirations */
    private final static int VERSION = 2;

    private final VariableExpiration[] expirations;

    private VariableExpirations(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = version >= 2 ? Utils.createLocalParcelToRead(parcel) : parcel;

        this.expirations = local.createTypedArray(VariableExpiration.CREATOR);

        if (version >= 2) Utils.endLocalParcelRead(local);
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
        Utils.writeToParcel(parcel, local -> local.writeTypedArray(expirations, flags));
    }

    public static Parcelable.Creator<VariableExpirations> CREATOR = new Parcelable.Creator<VariableExpirations>() {
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
