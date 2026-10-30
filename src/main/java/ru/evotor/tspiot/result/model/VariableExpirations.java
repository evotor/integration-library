package ru.evotor.tspiot.result.model;

import android.os.Parcel;

import androidx.annotation.NonNull;
import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;

/** Вариативный срок годности */
public class VariableExpirations extends BaseTsPioTObject {

    /** Версия VariableExpirations */
    private final static int VERSION = 2;

    private final VariableExpiration[] expirations;

    private VariableExpirations(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.expirations = local.createTypedArray(VariableExpiration.CREATOR);

        endLocalParcelRead(local, version, 2);
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
        writeToParcel(parcel, local -> {
            local.writeTypedArray(expirations, flags);
        });
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
