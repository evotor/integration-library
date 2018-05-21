package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultVoid extends AbstractResult implements Parcelable {

    private final ErrorDescription errorDescription;

    public ResultVoid(DeviceServiceException exc) {
        this.errorDescription = packException(exc);
    }

    private ResultVoid(Parcel parcel) {
        errorDescription = new ErrorDescription(parcel);
    }

    public void processAnswer() throws DeviceServiceException {
        this.unpackException();
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultVoid> CREATOR = new Creator<ResultVoid>() {

        public ResultVoid createFromParcel(Parcel in) {
            return new ResultVoid(in);
        }

        public ResultVoid[] newArray(int size) {
            return new ResultVoid[size];
        }
    };

}
