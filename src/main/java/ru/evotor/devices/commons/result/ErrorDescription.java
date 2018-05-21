package ru.evotor.devices.commons.result;

import android.os.Parcel;

import ru.evotor.devices.commons.exception.error_extension.AbstractErrorExtension;

public class ErrorDescription extends AbstractInnerParcelable {

    private final int errorCode;
    private final String errorUserDescription;
    private final AbstractErrorExtension errorExtension;

    public ErrorDescription(int errorCode, String errorUserDescription) {
        this(errorCode, errorUserDescription, null);
    }

    public ErrorDescription(int errorCode, String errorUserDescription, AbstractErrorExtension errorExtension) {
        this.errorCode = errorCode;
        this.errorUserDescription = errorUserDescription;
        this.errorExtension = errorExtension;
    }

    public ErrorDescription(Parcel parcel) {
        errorCode = parcel.readInt();
        errorUserDescription = parcel.readString();
        errorExtension = AbstractResult.createErrorExtension(errorCode, parcel);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorUserDescription() {
        return errorUserDescription;
    }

    public AbstractErrorExtension getErrorExtension() {
        return errorExtension;
    }

    public void writeToParcel(Parcel parcel) {
        parcel.writeInt(errorCode);
        parcel.writeString(errorUserDescription);
        if (errorExtension != null) {
            errorExtension.writeToParcel(parcel);
        }
    }

}
