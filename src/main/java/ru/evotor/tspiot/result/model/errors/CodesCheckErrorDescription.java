package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public class CodesCheckErrorDescription implements ErrorDescription {

    /** Версия CodesCheckErrorDescription */
    private final static int VERSION = 1;

    @Nullable private final Class<? extends ErrorDescription> onlineErrorClass;

    @Nullable private final Class<? extends ErrorDescription> offlineErrorClass;

    /** Ошибка онлайн проверки */
    @Nullable private final ErrorDescription onlineError;

    /** Ошибка офлайн проверки */
    @Nullable private final ErrorDescription offlineError;

    private CodesCheckErrorDescription(Parcel parcel) {
        int version = parcel.readInt();
        this.onlineErrorClass = Utils.readClass(parcel);
        this.offlineErrorClass = Utils.readClass(parcel);
        this.onlineError = Utils.readData(onlineErrorClass, parcel);
        this.offlineError = Utils.readData(offlineErrorClass, parcel);
    }

    public CodesCheckErrorDescription(@Nullable ErrorDescription onlineError, @Nullable ErrorDescription offlineError) {
        this.onlineErrorClass = onlineError != null ? onlineError.getClass() : null;
        this.offlineErrorClass = offlineError != null ? offlineError.getClass() : null;
        this.onlineError = onlineError;
        this.offlineError = offlineError;
    }

    @Nullable
    public ErrorDescription getOnlineError() { return onlineError; }

    @Nullable
    public ErrorDescription getOfflineError() { return offlineError; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeSerializable(onlineErrorClass);
        parcel.writeSerializable(offlineErrorClass);
        parcel.writeParcelable(onlineError, flags);
        parcel.writeParcelable(offlineError, flags);
    }

    @NotNull
    @Override
    public String toString() {
        return "OnlineError:\n" + Utils.toString(onlineError) + "\n" +
                "OfflineError:\n" + Utils.toString(offlineError) + "\n";
    }

    public static final Creator<CodesCheckErrorDescription> CREATOR = new Creator<>() {
        @Override
        public CodesCheckErrorDescription createFromParcel(Parcel parcel) {
            return new CodesCheckErrorDescription(parcel);
        }

        @Override
        public CodesCheckErrorDescription[] newArray(int size) {
            return new CodesCheckErrorDescription[size];
        }
    };
}
