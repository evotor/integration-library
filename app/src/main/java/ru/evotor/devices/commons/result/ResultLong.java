package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultLong extends AbstractResult implements Parcelable {

    private final long result;
    private final ErrorDescription errorDescription;

    public ResultLong(long result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultLong(Parcel parcel) {
        result = parcel.readLong();
        errorDescription = new ErrorDescription(parcel);
    }

    public long getResult() throws DeviceServiceException {
        this.unpackException();
        return this.result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(result);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultLong> CREATOR = new Creator<ResultLong>() {

        public ResultLong createFromParcel(Parcel in) {
            return new ResultLong(in);
        }

        public ResultLong[] newArray(int size) {
            return new ResultLong[size];
        }
    };

}
