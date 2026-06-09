package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;

import org.jetbrains.annotations.NotNull;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public class MessageErrorDescription implements ErrorDescription {

    /** Версия MessageErrorDescription */
    private final static int VERSION = 1;

    /** Описание ошибки */
    private final String message;

    private MessageErrorDescription(Parcel parcel) {
        int version = parcel.readInt();
        this.message = parcel.readString();
    }

    public MessageErrorDescription(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(VERSION);
        parcel.writeString(message);
    }

    @NotNull
    @Override
    public String toString() {
        return "Message: " + Utils.toString(message) + "\n";
    }

    public static final Creator<MessageErrorDescription> CREATOR = new Creator<MessageErrorDescription>() {
        @Override
        public MessageErrorDescription createFromParcel(Parcel parcel) {
            return new MessageErrorDescription(parcel);
        }

        @Override
        public MessageErrorDescription[] newArray(int size) {
            return new MessageErrorDescription[size];
        }
    };
}
