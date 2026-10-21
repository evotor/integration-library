package ru.evotor.tspiot.result;

import android.os.Parcel;

import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;
import ru.evotor.tspiot.result.model.base.ErrorDescription;
import ru.evotor.tspiot.result.model.errors.TsPioTErrorsDescriptionWrapper;

public class TsPioTError extends BaseTsPioTObject {

    /** Версия TsPioTError */
    private final static int VERSION = 2;

    /** Описание ошибки */
    private final TsPioTErrorsDescriptionWrapper<? extends ErrorDescription> error;

    private final Class<? extends TsPioTErrorsDescriptionWrapper<? extends ErrorDescription>> errorType;

    private TsPioTError(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        errorType = Utils.readClass(local);
        error = Utils.readData(errorType, local);

        endLocalParcelRead(local, version, 2);
    }

    @SuppressWarnings("unchecked")
    public TsPioTError(TsPioTErrorsDescriptionWrapper<? extends ErrorDescription> error) {
        this.error = error;
        this.errorType = (Class<? extends TsPioTErrorsDescriptionWrapper<? extends ErrorDescription>>) error.getClass();
    }

    public TsPioTErrorsDescriptionWrapper<? extends ErrorDescription> getError() { return error; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeSerializable(errorType);
            local.writeParcelable(error, flags);
        });
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
