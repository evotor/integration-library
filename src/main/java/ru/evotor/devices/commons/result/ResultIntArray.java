package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;

public class ResultIntArray extends AbstractResult implements Parcelable {

    private final int[] result;
    private final ErrorDescription errorDescription;

    public ResultIntArray(int[] result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultIntArray(Parcel parcel) {
        int resultLength = parcel.readInt();
        result = new int[resultLength];
        if (resultLength > 0) {
            parcel.readIntArray(result);
        }
        errorDescription = new ErrorDescription(parcel);
    }

    public int[] getResult() throws DeviceServiceException {
        this.unpackException();
        return this.result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(result == null ? 0 : result.length);
        parcel.writeIntArray(result);
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultIntArray> CREATOR = new Creator<ResultIntArray>() {

        public ResultIntArray createFromParcel(Parcel in) {
            return new ResultIntArray(in);
        }

        public ResultIntArray[] newArray(int size) {
            return new ResultIntArray[size];
        }
    };

}
