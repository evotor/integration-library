package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

import androidx.annotation.NonNull;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public class CodeMessageErrorDescription implements ErrorDescription {

    /** Версия CommonErrorDescription */
    private final static int VERSION = 1;

    /** Код ошибки */
    private final int errorCode;

    /** Описание ошибки */
    private final String message;

    private CodeMessageErrorDescription(Parcel parcel) {
        int version = parcel.readInt();
        this.errorCode = parcel.readInt();
        this.message = parcel.readString();
    }

    public CodeMessageErrorDescription(int errorCode, String message) {
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

    @NonNull
    @Override
    public String toString() {
        return "ErrorCode: " + Utils.toString(errorCode) + "\n" +
                "Message: " + Utils.toString(message) + "\n";
    }

    public static final Creator<CodeMessageErrorDescription> CREATOR = new Creator<>() {
        @Override
        public CodeMessageErrorDescription createFromParcel(Parcel parcel) {
            return new CodeMessageErrorDescription(parcel);
        }

        @Override
        public CodeMessageErrorDescription[] newArray(int size) {
            return new CodeMessageErrorDescription[size];
        }
    };
}
