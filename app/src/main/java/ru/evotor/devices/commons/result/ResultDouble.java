package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultDouble extends AbstractResult implements Parcelable {

    private final double result;
    private final ErrorDescription errorDescription;

    public ResultDouble(double result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultDouble(Parcel parcel) {
        result = parcel.readDouble();
        errorDescription = new ErrorDescription(parcel);
    }

    public double getResult() throws DeviceServiceException {
        this.unpackException();
        return this.result;
    }


    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(result);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultDouble> CREATOR = new Creator<ResultDouble>() {

        public ResultDouble createFromParcel(Parcel in) {
            return new ResultDouble(in);
        }

        public ResultDouble[] newArray(int size) {
            return new ResultDouble[size];
        }
    };

}
