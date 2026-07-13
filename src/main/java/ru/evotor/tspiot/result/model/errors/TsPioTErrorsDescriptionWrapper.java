package ru.evotor.tspiot.result.model.errors;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import ru.evotor.tspiot.result.model.base.ErrorDescription;

public abstract sealed class TsPioTErrorsDescriptionWrapper<T extends ErrorDescription> implements Parcelable permits CheckServiceAreUnavailable, CodeMessageErrorDescriptionWrapper, MessageErrorDescriptionWrapper {
    abstract public T getErrorDescription();

    protected TsPioTErrorsDescriptionWrapper() { }

    protected TsPioTErrorsDescriptionWrapper(Parcel parcel) { }

    public static class BaseCreator<I extends TsPioTErrorsDescriptionWrapper<? extends ErrorDescription>> implements Creator<I> {
        private final Class<I> clazz;

        public BaseCreator(Class<I> clazz) { this.clazz = clazz; }

        @SuppressWarnings("unchecked")
        @Override
        public I createFromParcel(Parcel in) {
            try {
                Constructor<I> constructor = clazz.getDeclaredConstructor(Parcel.class);
                constructor.setAccessible(true);
                return constructor.newInstance(in);
            } catch (Exception e) {
                throw new RuntimeException("Cannot create " + clazz.getSimpleName(), e);
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public I[] newArray(int size) { return (I[]) Array.newInstance(clazz, size); }
    }
}
