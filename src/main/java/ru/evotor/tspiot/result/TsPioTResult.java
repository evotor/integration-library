package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.ParcelablesKt;
import ru.evotor.tspiot.BaseTsPioTObject;
import ru.evotor.tspiot.Utils;

public class TsPioTResult<T extends Parcelable> extends BaseTsPioTObject {

    /** Версия TsPioTResult */
    private final static int VERSION = 2;

    @Nullable
    private final Class<T> classType;

    @Nullable private final TsPioTError error;

    @Nullable private final T data;

    private TsPioTResult(Parcel parcel) {
        int version = parcel.readInt();
        Parcel local = createLocalParcelToRead(parcel, version, 2);

        this.classType = Utils.readClass(local);
        this.data = Utils.readData(classType, local);
        this.error = ParcelablesKt.readParcelable(local, TsPioTError.class);

        endLocalParcelRead(local, version, 2);
    }

    @SuppressWarnings("unchecked")
    public TsPioTResult(T data) {
        this.classType = (Class<T>) data.getClass();
        this.data = data;
        this.error = null;
    }

    public TsPioTResult(@NonNull TsPioTError error) {
        this.classType = null;
        this.data = null;
        this.error = error;
    }

    @Nullable
    public T getData() { return data; }

    @Nullable
    public TsPioTError getError() { return error; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(VERSION);
        writeToParcel(parcel, local -> {
            local.writeSerializable(classType);
            local.writeParcelable(data, flags);
            local.writeParcelable(error, flags);
        });
    }

    public static final Parcelable.Creator<TsPioTResult<? extends Parcelable>> CREATOR = new Parcelable.Creator<>() {
        @Override
        public TsPioTResult<?> createFromParcel(Parcel parcel) {
            return new TsPioTResult<>(parcel);
        }

        @Override
        public TsPioTResult<?>[] newArray(int size) {
            return new TsPioTResult[size];
        }
    };
}
