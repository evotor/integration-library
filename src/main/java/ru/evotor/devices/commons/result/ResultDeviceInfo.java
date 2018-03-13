package ru.evotor.devices.commons.result;

import android.os.Parcel;
import android.os.Parcelable;

import ru.evotor.devices.commons.exception.DeviceServiceException;
import ru.evotor.devices.commons.service.DeviceInfo;

public class ResultDeviceInfo extends AbstractResult implements Parcelable {

    private final DeviceInfo result;
    private final ErrorDescription errorDescription;

    public ResultDeviceInfo(DeviceInfo result, DeviceServiceException exc) {
        this.result = result;
        this.errorDescription = packException(exc);
    }

    private ResultDeviceInfo(Parcel parcel) {
        result = new DeviceInfo(parcel);
        errorDescription = new ErrorDescription(parcel);
    }

    public DeviceInfo getResult() throws DeviceServiceException {
        this.unpackException();
        return result;
    }

    @Override
    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (result != null) {
            result.writeToParcel(parcel);
        } else {
            new DeviceInfo(null, null, null, null, null).writeToParcel(parcel);
        }
        if (errorDescription != null) {
            errorDescription.writeToParcel(parcel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultDeviceInfo> CREATOR = new Creator<ResultDeviceInfo>() {

        public ResultDeviceInfo createFromParcel(Parcel in) {
            return new ResultDeviceInfo(in);
        }

        public ResultDeviceInfo[] newArray(int size) {
            return new ResultDeviceInfo[size];
        }
    };

}
