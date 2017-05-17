package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultBoolean extends AbstractResult implements Parcelable {

    private final boolean result;
    private final ErrorDescription errorDescription;

    public ResultBoolean(boolean result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultBoolean(Parcel parcel) {
        result = parcel.readInt() > 0;
        errorDescription = new ErrorDescription(parcel);
    }

    public boolean getResult() throws DeviceServiceException {
        this.unpackException();
        return this.result;
    }

    public boolean isResult() {
        return result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(result ? 1 : 0);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultBoolean> CREATOR = new Creator<ResultBoolean>() {

        public ResultBoolean createFromParcel(Parcel in) {
            return new ResultBoolean(in);
        }

        public ResultBoolean[] newArray(int size) {
            return new ResultBoolean[size];
        }
    };
}
