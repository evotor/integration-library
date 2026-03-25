package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;

public class IntegrationError implements Parcelable {

    private final int code;

    private final String message;

    private IntegrationError(Parcel parcel) {
        this.code = parcel.readInt();
        this.message = parcel.readString();
    }

    public IntegrationError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(code);
        parcel.writeString(message);
    }

    public static final Creator<IntegrationError> CREATOR = new Creator<>() {
        @Override
        public IntegrationError createFromParcel(Parcel parcel) {
            return new IntegrationError(parcel);
        }

        @Override
        public IntegrationError[] newArray(int i) {
            return new IntegrationError[i];
        }
    };
}
