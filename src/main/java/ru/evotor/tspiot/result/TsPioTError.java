package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.ErrorDescription;
import ru.evotor.tspiot.result.model.errors.TsPioTErrorsDescription;

public class TsPioTError implements Parcelable {

    /** Версия TsPioTError */
    private final static int VERSION = 1;

    /** Описание ошибки */
    private final TsPioTErrorsDescription<? extends ErrorDescription> error;

    private final Class<? extends TsPioTErrorsDescription<? extends ErrorDescription>> errorType;

    private TsPioTError(Parcel parcel) {
        int version = parcel.readInt();
        errorType = Utils.readClass(parcel);
        error = Utils.readData(errorType, parcel);
    }

    @SuppressWarnings("unchecked")
    public TsPioTError(TsPioTErrorsDescription<? extends ErrorDescription> error) {
        this.error = error;
        this.errorType = (Class<? extends TsPioTErrorsDescription<? extends ErrorDescription>>) error.getClass();
    }

    public TsPioTErrorsDescription<? extends ErrorDescription> getError() { return error; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        parcel.writeSerializable(errorType);
        parcel.writeParcelable(error, flags);
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
