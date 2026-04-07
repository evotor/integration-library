package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;

public enum Errors implements Parcelable {
    NOT_REGISTERED,
    NOT_CONFIGURED,
    ONLINE_CHECK_FAILED,
    OFFLINE_CHECK_FAILED,
    KKM_ERROR,
    FN_SID_ERROR,
    NOT_FOUND_PERMISSION,
    NOT_FOUND_PMSR_ID,
    NOT_FOUND_PMSR_TOKEN,
    FAILED_GET_APP_NAME,
    FAILED_GET_APP_VERSION,
    UNKNOWN_ERROR;

    /** Версия Errors */
    public final static int VERSION = 1;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeString(this.name());
    }

    @Nullable
    public static Errors restoreFormParcel(Parcel parcel) {
        int version = parcel.readInt();

        try {
            return Errors.valueOf(parcel.readString());
        } catch (Exception exception) {
            if (version != VERSION) {
                return Errors.UNKNOWN_ERROR;
            } else {
                return null;
            }
        }
    }

    public final static Creator<Errors> CREATOR = new Creator<>() {
        @Override
        public Errors createFromParcel(Parcel parcel) {
            return restoreFormParcel(parcel);
        }

        @Override
        public Errors[] newArray(int size) {
            return new Errors[size];
        }
    };
}
