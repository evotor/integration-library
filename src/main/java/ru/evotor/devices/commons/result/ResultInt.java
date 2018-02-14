package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultInt extends AbstractResult implements Parcelable {

    private final int result;
    private final ErrorDescription errorDescription;

    public ResultInt(int result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultInt(Parcel parcel) {
        result = parcel.readInt();
        errorDescription = new ErrorDescription(parcel);
    }

    public int getResult() throws DeviceServiceException {
        this.unpackException();
        return result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(result);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultInt> CREATOR = new Creator<ResultInt>() {

        public ResultInt createFromParcel(Parcel in) {
            return new ResultInt(in);
        }

        public ResultInt[] newArray(int size) {
            return new ResultInt[size];
        }
    };

}
