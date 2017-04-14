package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultString extends AbstractResult implements Parcelable {

    private final String result;
    private final ErrorDescription errorDescription;

    public ResultString(String result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultString(Parcel parcel) {
        result = parcel.readString();
        errorDescription = new ErrorDescription(parcel);
    }

    public String getResult() throws DeviceServiceException {
        this.unpackException();
        return this.result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(result);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultString> CREATOR = new Creator<ResultString>() {

        public ResultString createFromParcel(Parcel in) {
            return new ResultString(in);
        }

        public ResultString[] newArray(int size) {
            return new ResultString[size];
        }
    };

}
