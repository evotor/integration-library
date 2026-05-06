package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public abstract sealed class TsPioTErrorsDescription<T extends ErrorDescription> implements Parcelable permits CheckServiceAreUnavailable, CommonErrorDescriptionError, MessageErrorDescriptionError {
    abstract public T getErrorDescription();

    protected TsPioTErrorsDescription() { }

    protected TsPioTErrorsDescription(Parcel parcel) { }

    public static class BaseCreator<I extends TsPioTErrorsDescription<? extends ErrorDescription>> implements Creator<I> {
        private final Class<I> clazz;

        public BaseCreator(Class<I> clazz) { this.clazz = clazz; }

        @SuppressWarnings("unchecked")
        @Override
        public I createFromParcel(Parcel in) {
            try {
                Constructor<I> ctor = clazz.getDeclaredConstructor(Parcel.class);
                ctor.setAccessible(true);
                return ctor.newInstance(in);
            } catch (Exception e) {
                throw new RuntimeException("Cannot create " + clazz.getSimpleName(), e);
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public I[] newArray(int size) { return (I[]) Array.newInstance(clazz, size); }
    }
}