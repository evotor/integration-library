package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;

public class TsPioTError implements Parcelable {

    /** Версия IntegrationError */
    private final static int VERSION = 1;

    private final Errors code;

    private final String message;

    private TsPioTError(Parcel parcel) {
        int version = parcel.readInt();
        this.code = parcel.readParcelable(Errors.class.getClassLoader());
        this.message = parcel.readString();
    }

    public TsPioTError(Errors code, String message) {
        this.code = code;
        this.message = message;
    }

    public Errors getCode() { return code; }

    public String getMessage() { return message; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeParcelable(code, flags);
        parcel.writeString(message);
    }

    public static final Creator<TsPioTError> CREATOR = new Creator<>() {
        @Override
        public TsPioTError createFromParcel(Parcel parcel) {
            return new TsPioTError(parcel);
        }

        @Override
        public TsPioTError[] newArray(int size) {
            return new TsPioTError[size];
        }
    };
}
