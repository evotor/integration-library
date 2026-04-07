package ru.evotor.tspiot.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.Serializable;

public class TsPioTResult<T extends Parcelable> implements Parcelable {

    /** Версия TsPioTResult */
    private final static int VERSION = 1;

    @Nullable
    private final Class<T> classType;

    @Nullable private final TsPioTError error;

    @Nullable private final T data;

    private TsPioTResult(Parcel parcel) {
        int version = parcel.readInt();
        this.classType = upcastClassType(parcel.readSerializable());
        this.data = parseData(classType, parcel);
        this.error = parcel.readParcelable(TsPioTError.class.getClassLoader());
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private Class<T> upcastClassType(Serializable serializable) {
        if (serializable == null) {
            return null;
        }

        try {
            return (Class<T>) serializable;
        } catch (Exception exception) {
            return null;
        }
    }

    @Nullable
    private T parseData(Class<T> classType, Parcel parcel) {
        try {
            if (classType == null) {
                return parcel.readParcelable(null);
            }

            return parcel.readParcelable(classType.getClassLoader());
        } catch (Exception exception) {
            return null;
        }
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
        parcel.writeSerializable(classType);
        parcel.writeParcelable(data, flags);
        parcel.writeParcelable(error, flags);
    }

    public static final Creator<TsPioTResult<? extends Parcelable>> CREATOR = new Creator<>() {
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
