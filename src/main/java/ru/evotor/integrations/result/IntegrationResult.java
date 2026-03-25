package ru.evotor.integrations.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.io.Serializable;

public class IntegrationResult<T extends Parcelable> implements Parcelable {

    private final Class<T> classType;

    private final T data;

    private IntegrationResult(Parcel parcel) {
        this.classType = upcastClassType(parcel.readSerializable());
        this.data = parseData(classType, parcel);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private Class<T> upcastClassType(Serializable serializable) {
        try {
            return (Class<T>) serializable;
        } catch (Exception exception) {
            return null;
        }
    }

    @Nullable
    private T parseData(Class<T> classType, Parcel parcel) {
        try {
            return parcel.readParcelable(classType.getClassLoader());
        } catch (Exception exception) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public IntegrationResult(T data) {
        this.classType = (Class<T>) data.getClass();
        this.data = data;
    }

    public T getData() { return data; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(classType);
        parcel.writeParcelable(data, flags);
    }

    public static final Creator<IntegrationResult<? extends Parcelable>> CREATOR = new Creator<>() {
        @Override
        public IntegrationResult<?> createFromParcel(Parcel parcel) {
            return new IntegrationResult<>(parcel);
        }

        @Override
        public IntegrationResult<?>[] newArray(int i) {
            return new IntegrationResult[0];
        }
    };
}
