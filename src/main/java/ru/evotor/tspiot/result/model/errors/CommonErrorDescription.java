package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public class CommonErrorDescription implements ErrorDescription {

    /** Версия CommonErrorDescription */
    private final static int VERSION = 1;

    /** Код ошибки */
    private final int errorCode;

    /** Описание ошибки */
    private final String message;

    private CommonErrorDescription(Parcel parcel) {
        int version = parcel.readInt();
        this.errorCode = parcel.readInt();
        this.message = parcel.readString();
    }

    public CommonErrorDescription(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() { return errorCode; }

    public String getMessage() { return message; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeInt(errorCode);
        parcel.writeString(message);
    }

    public static final Creator<CommonErrorDescription> CREATOR = new Creator<>() {
        @Override
        public CommonErrorDescription createFromParcel(Parcel parcel) {
            return new CommonErrorDescription(parcel);
        }

        @Override
        public CommonErrorDescription[] newArray(int size) {
            return new CommonErrorDescription[size];
        }
    };
}
